package in.yjawaid.product_service_110524.advices;

import in.yjawaid.product_service_110524.dtos.ErrorDTO;
import in.yjawaid.product_service_110524.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException (ProductNotFoundException productNotFoundException)
    {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(productNotFoundException.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
