package xtqh.business;

import java.util.Map;

import xtqh.business.bean.BResource;
import xtqh.business.exception.BusinessException;
import xtqh.framework.base.paginate.Pagination;
import xtqh.framework.base.paginate.PaginationParameter;

public interface BResourceService {

	public Pagination<BResource> getResourceList(Map<String, String> parMap, PaginationParameter parameter)
			throws BusinessException;

	public Pagination<BResource> getResourceList(PaginationParameter parameter) throws BusinessException;
}
