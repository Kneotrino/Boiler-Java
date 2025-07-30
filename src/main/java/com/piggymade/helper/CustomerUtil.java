package com.piggymade.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;


@Slf4j
public class CustomerUtil {
    public static String deleteCompanyFromCustomerName(String customerName){
        try {
            final List<String> collect = Arrays.stream(customerName.split(" ..")).toList();
            if (!collect.isEmpty())
                return collect.get(1);
            else
                return collect.get(0);
        } catch (Exception ex) {
            log.error("error deleteCompanyFromCustomerName = {} ; Return default", customerName);
            return customerName;
        }
    }
}
