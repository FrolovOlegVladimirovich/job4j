package ru.job4j.crudservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DBStore.class, UsersUpdateController.class, LogManager.class})
public class UsersUpdateControllerTest {
    private static Store store = new StoreStub();

    @Test
    public void whenUpdateUserThenReturnNewName() throws Exception {
        User model = new User();
        model.setId("1");
        model.setName("Oleg");
        model.setRole("user");
        store.add(model);
        mockStatic(LogManager.class);
        mockStatic(DBStore.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Logger logger = mock(Logger.class);
        Whitebox.setInternalState(UsersUpdateController.class, "LOG", logger);
        doNothing().when(logger).info(isA(String.class));
        when(DBStore.getINSTANCE()).thenReturn(store);
        when(req.getParameter("action")).thenReturn("update");
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("NewName");
        when(req.getParameter("role")).thenReturn("user");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("model")).thenReturn(model);
        when(req.getRequestDispatcher("/WEB-INF/views/list.jsp")).thenReturn(dispatcher);

        new UsersUpdateController().doPost(req, resp);

        assertThat(store.getUserById(model).getName(), is("NewName"));
    }

    @Test
    public void whenDeleteUserThenUserNotFound() throws Exception {
        User model = new User();
        model.setName("Oleg");
        model.setRole("user");
        model.setId("2");
        store.add(model);
        mockStatic(LogManager.class);
        mockStatic(DBStore.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Logger logger = mock(Logger.class);
        Whitebox.setInternalState(UsersUpdateController.class, "LOG", logger);
        doNothing().when(logger).info(isA(String.class));
        when(DBStore.getINSTANCE()).thenReturn(store);
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getParameter("id")).thenReturn("2");
        when(req.getParameter("name")).thenReturn("Oleg");
        when(req.getParameter("role")).thenReturn("user");
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("model")).thenReturn(model);
        when(req.getRequestDispatcher("/WEB-INF/views/list.jsp")).thenReturn(dispatcher);

        new UsersUpdateController().doPost(req, resp);

        assertNull(store.getUserById(model));
    }
}