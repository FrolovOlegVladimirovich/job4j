package ru.job4j.crudservlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DBStore.class, UsersCreateController.class, LogManager.class})
public class UsersCreateControllerTest {
    private static final List<FileItem> ITEMS = new ArrayList<>();

    @BeforeClass
    public static void setUp() throws IOException {
        Map<String, String> inputParameters = Map.of(
                "name", "Oleg",
                "login", "OlegLogin",
                "email", "oleg@oleg.ru",
                "password", "qwerty",
                "role", "user"
        );
        FileItemFactory factory = new DiskFileItemFactory();
        for (var entry :inputParameters.entrySet()) {
            String value = entry.getValue();
            FileItem item = factory.createItem(entry.getKey(), "text", true, value);
            item.getOutputStream().write(value.getBytes());
            ITEMS.add(item);
        }
        var image = factory.createItem("file", "file", false, "ImageName.jpg");
        image.getOutputStream().write("ImageName.jpg".getBytes());
        ITEMS.add(image);
    }

    @Test
    public void whenAddUserThenStoreIt() throws Exception {
        Store store = new StoreStub();
        mockStatic(LogManager.class);
        mockStatic(DBStore.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletContext context = mock(ServletContext.class);
        ServletConfig config = mock(ServletConfig.class);
        ServletFileUpload upload = mock(ServletFileUpload.class);
        Logger logger = mock(Logger.class);
        Whitebox.setInternalState(UsersCreateController.class, "LOG", logger);
        doNothing().when(logger).info(isA(String.class));
        when(DBStore.getINSTANCE()).thenReturn(store);
        UsersCreateController servlet = spy(new UsersCreateController());
        when(servlet.getServletConfig()).thenReturn(config);
        when(config.getServletContext()).thenReturn(context);
        whenNew(ServletFileUpload.class).withAnyArguments().thenReturn(upload);
        when(upload.parseRequest(req)).thenReturn(ITEMS);
        doNothing().when(servlet, "saveImage", anyString(), any(FileItem.class));

        servlet.doPost(req, resp);

        assertThat(store.findAll().iterator().next().getName(), is("Oleg"));
        verify(resp, times(1)).sendRedirect(req.getContextPath());
    }
}