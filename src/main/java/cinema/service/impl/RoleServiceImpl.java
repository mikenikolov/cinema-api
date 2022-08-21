package cinema.service.impl;

import cinema.dao.RoleDao;
import cinema.model.Role;
import cinema.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    public static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        Role createdRole = roleDao.add(role);
        logger.info("A new role has been created. Params:{roleId:{}, roleName:{}}",
                createdRole.getId(), createdRole.getRoleName());
        return createdRole;
    }

    @Override
    public Role getByName(String roleName) {
        return roleDao.getByName(roleName).orElseThrow(() ->
                new RuntimeException("Role with name: " + roleName + " not found"));
    }
}
