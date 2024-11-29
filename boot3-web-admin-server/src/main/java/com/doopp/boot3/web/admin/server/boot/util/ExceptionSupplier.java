package com.doopp.boot3.web.admin.server.boot.util;

    
@FunctionalInterface
public interface ExceptionSupplier {

    void apply() throws Exception;
}
