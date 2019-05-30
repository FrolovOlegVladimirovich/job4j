package ru.job4j.profession;

/**
 * Класс-наследник Профессии: Доктор.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 30.05.2019
 * @version 1.0
 */
public class Doctor extends Profession {
    private String diagnose;
    private String disease;
    private int patients;

    /**
     * Стандартный конструктор класса Doctor.
     */
    public Doctor() {
    }

    /**
     * @param diagnose Присваивает название диагноза.
     * @param disease Присваивает название болезни.
     */
    public Doctor(String diagnose, String disease) {
        this.diagnose = diagnose;
        this.disease = disease;
    }


    /**
     * @param patients Присваивает количество пациентов.
     */
    public Doctor(int patients) {
        this.patients = patients;
    }

    /**
     * Возвращает количество пациентов
     * @return patients
     */
    public int getPatients() {
        return patients;
    }

    /**
     * Возвращает название болезни.
     * @return disease
     */
    public String getDisease() {
        return disease;
    }

    /**
     * Возвращает название диагноза.
     * @return diagnose
     */
    public String getDiagnose() {
        return diagnose;
    }
}
