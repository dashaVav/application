package com.example.application.service;

import com.example.application.dto.LoanApplicationRequestDTO;
import com.example.application.exception.PrescoringException;
import com.example.application.service.impl.PrescoringServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrescoringServiceImplTest {
    private final PrescoringService prescoringService = new PrescoringServiceImpl();
    LoanApplicationRequestDTO data;

    @BeforeEach
    void setUp() {
        data = new LoanApplicationRequestDTO(
                BigDecimal.valueOf(10000),
                6,
                "firstName",
                "lastName",
                "middleName",
                "email@email.com",
                LocalDate.of(2002, 10, 11),
                "1111",
                "111111");
    }

    @Test
    void testValidationOfLoanApplicationRequestWithoutThrows() {
        assertDoesNotThrow(() -> prescoringService.validationOfLoanApplicationRequest(data));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Имя", "N", "Namenamenamenamenamenamenamenamenamenamenamenamenamenamename", "Name1"})
    void testValidationOfFirstNameThrowException(String firstName) {
        data.setFirstName(firstName);
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "First name must contain between 2 and 30 Latin letters";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Имя", "N", "Namenamenamenamenamenamenamenamenamenamenamenamenamenamename", "Name1"})
    void testValidationOfLastNameThrowException(String lastName) {
        data.setLastName(lastName);
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "Last name must contain between 2 and 30 Latin letters";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Имя", "N", "Namenamenamenamenamenamenamenamenamenamenamenamenamenamename", "Name1"})
    void testValidationOfMiddleNameThrowException(String middleName) {
        data.setMiddleName(middleName);
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "Middle name must contain between 2 and 30 Latin letters";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testValidationOfCreditAmountThrowException() {
        data.setAmount(BigDecimal.valueOf(9999));
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "Amount must be number greater than or equal to 10,0000";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testValidationOfTermThrowException() {
        data.setTerm(5);
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "Term must be number greater than or equal to 6";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testValidationOfBirthdateThrowException() {
        data.setBirthdate(LocalDate.now());
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "Client must be over 18 years old";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"email", "@xx.com", "d@1.c", "test@email", "test@", "test@gmail.commmmmmmmmmmmmmmmmmmm"})
    void testValidationOfEmailThrowException(String email) {
        data.setEmail(email);
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "Email must match the pattern [\\w.]{2,50}@[\\w.]{2,20}\\.[\\w.]{2,10}";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "22", "333", "55555", "test"})
    void testValidationOfPassportSeriesThrowException(String passportSeries) {
        data.setPassportSeries(passportSeries);
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "Passport series must contain 4 digits";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "22", "333", "4444", "55555", "testik"})
    void testValidationOfPassportNumberThrowException(String passportNumber) {
        data.setPassportNumber(passportNumber);
        Exception exception = assertThrows(PrescoringException.class, () -> prescoringService.validationOfLoanApplicationRequest(data));
        String expectedMessage = "Passport number must contain 6 digits";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
