package com.stock.chitchat.user.dto;

import com.stock.chitchat.user.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailResponseDTO {
    private String email;
    private String name;

    public static UserDetailResponseDTO fromEntity(User user) {
        return UserDetailResponseDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}

/**
 * 1 VM, JDBC
 * => internal 어쩌고
 * 2 24/7 HA
 * => point-in-time, Schedule 어쩌고 backups
 * 3 move to Google Cloud, VMWare
 * => VMware Engine
 * 4 Cloud Spanner
 * => SPANNER_SYS.READ_STATS
 * 5 PostgreSQL AWS
 * => DB Migration 어쩌고 Cloud SQL
 * 6 Bare Metal Solution
 * => Cloud NAT 어쩌고 Compute Engine VM
 * 7 MySQL in Cloud SQL
 * => Use Cloud Monitoring
 * 8 retail, ecommerce, Google Cloud
 * => Deploy Cloud Spanner
 * 9 7 days a week, 6 AM to 10 PM
 * => maintenance 어쩌고. 긴 거
 * 10 new version of highly ~~ user traffic
 * => Identify and optimize slow running queires~~
 * 11 SoC PoLP
 * => Admin A, Reader B, Writer C
 * 12 game iOS Android
 * => granular instance ~~ Autoscaler
 * 13 a new product in US market. ~~ APAC
 * => 35%, us, us, asai, asai
 * 14
 */