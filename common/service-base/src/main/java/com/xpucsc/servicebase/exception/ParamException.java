package com.xpucsc.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author Abraham
 * @date 2022/10/22 7:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamException extends RuntimeException{

    List<FieldError> errors;

}
