package com.hitTheRoad.server;

import com.hitTheRoad.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminUtils {
    public static Admin getCurrentAdmin(){
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
