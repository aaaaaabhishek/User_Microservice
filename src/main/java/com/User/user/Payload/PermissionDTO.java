package com.User.user.Payload;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {
    private Long permissionId;
    private String permissionName;

    private Long roleId;
    private Long scopeId;

    @NotNull(message = "Primary group name can't be null")
    private String primaryGroupName;

    private List<String> secondaryGroup;
    private String languageName;
    private String organization;
    private String timeZone;
    private String memo;
}