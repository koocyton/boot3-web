package com.doopp.boot3.web.core.response;

import lombok.Data;

import java.io.Serializable;
import java.util.function.Supplier;

import com.doopp.boot3.web.core.exception.AssertException;

@Data
public class Result<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public static <D> Result<D> ok(D data) {
        Result<D> res = new Result<>();
        res.setCode("0");
        res.setMessage("success");
        res.setData(data);
        return res;
    }

    public static <D> Result<D> err(String code, String message) {
        Result<D> res = new Result<>();
        res.setCode(code);
        res.setMessage(message);
        return res;
    }

    public static <D> Result<D> tryOk(Supplier<D> supplier) {
        try {
            return ok(supplier.get());
        }
        catch (AssertException ae) {
            return err(ae.getCode(), ae.getMessage());
        }
        catch (Exception e) {
            return err("", e.getMessage());
        }
    }
}
