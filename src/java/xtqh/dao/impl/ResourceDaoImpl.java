package xtqh.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import xtqh.dao.ResourceDao;
import xtqh.dao.entity.Resource;
import xtqh.framework.base.BaseJdbcDao;

@Repository("ResourceDao")
public class ResourceDaoImpl extends BaseJdbcDao implements ResourceDao {

	private static RowMapper<Resource> resourceRowMapper = new RowMapper<Resource>() {

		@Override
		public Resource mapRow(ResultSet rs, int rownum) throws SQLException {
			Resource resource = new Resource();
			resource.setResourceId(rs.getString("id"));
			resource.setControllingIp(rs.getString("controlling_ip"));
			resource.setHostname(rs.getString("hostname"));
			resource.setName(rs.getString("name"));
			resource.setResourceType(rs.getString("resource_type"));
			return resource;
		}
	};

	@Override
	public List<Resource> fetchResourceList() {
		// TODO Auto-generated method stub
		String sql = "select distinct id, controlling_ip, name, resource_type,hostname from resource";
		List<Resource> resourceList = jdbcTemplate.query(sql, resourceRowMapper);

		return resourceList;
	}

}
