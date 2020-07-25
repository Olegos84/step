package org.itstep.selenium.product.yandex.common;

/**
 * Перечисление в котором будут храниться все сообщения об ошибках, которые бросает наше приложение, которое мы
 * тестируем. Это сделано, для того что бы в едином месте иметь текст ошибки. Если этот текст будет изменен,
 * не придется переписывать много классов, достаточно поменять только здесь
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 * @see java.lang.Enum
 */
public enum ErrorMessage {
  INCORRECT_PASSWORD("Неверный пароль");

  /**
   * Переменная, которая хранит текст сообщения
   */
  private String message;

  /**
   * Метод, который возвращает текст ошибки
   *
   * @return Текст сообщения об ошибке
   */
  public String getMessage() {
    return message;
  }

  /**
   * Конструктор, который используется для каждого значения перечисленного в Enum
   * @param message Текст ошибки, который пользователь видит на ui
   */
  ErrorMessage(String message) {
    this.message = message;
  }
}
