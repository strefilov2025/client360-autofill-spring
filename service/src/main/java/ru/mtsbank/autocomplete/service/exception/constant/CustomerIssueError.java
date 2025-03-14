package ru.mtsbank.autocomplete.service.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import ru.mtsbank.lib.web.model.enums.error.MtsBankErrorType;

@Getter
@RequiredArgsConstructor
public enum CustomerIssueError implements MtsBankErrorType {

    BAD_REQUEST("400", HttpStatus.BAD_REQUEST, "Неправильная структура запроса", "Некоррекный номер телефона [{0}]"),
    BAD_REQUEST_FIO("401", HttpStatus.BAD_REQUEST, "Неправильная структура запроса", "Некоррекные данные для запроса ФИО + ДР + ДУЛ [{0}]"),
    NOT_FOUND("204", HttpStatus.NO_CONTENT, "", ""),
    NOT_VALIDATION("421", HttpStatus.UNPROCESSABLE_ENTITY, "Не пройдена проверка валидности данных", ""),
    UNPROCESSABLE_ENTITY("423", HttpStatus.UNPROCESSABLE_ENTITY, "Не удалось дедуплицировать записи в автоматическом режиме", "результат больше одной записи"),
    UNPROCESSABLE_RBO("422", HttpStatus.UNPROCESSABLE_ENTITY, "Найдено более одной записи в системе-источнике", "результат больше одной записи"),
    INTERNAL_SERVER_ERROR("424", HttpStatus.FAILED_DEPENDENCY, "Внутренняя ошибка сервера", ""),

    BANK_SERVICE_ERROR_RESPONSE("425", HttpStatus.FAILED_DEPENDENCY, "Ошибка взаимодействия с внутренним сервисом банка", "");

    private final String code;
    private final HttpStatus httpStatus;
    private final String title;
    private final String details;
}
