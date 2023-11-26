package com.test.avaliable.infrastructure.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URLConstantes {

    public static final String BASE_URL = "/avaliacao/v1";
    public static final String BASE_URL_AUTHENTICATION = BASE_URL + "/autenticacao";
    public static final String AUTHENTICATION_URL = BASE_URL_AUTHENTICATION + "/login";
    public static final String BASE_URL_CONTATO = BASE_URL + "/contatos";
    public static final String BASE_URL_PROFISSIONAL = BASE_URL + "/profissionais";

}
