package xtqh.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 

  * @ClassName: NonstandardProperty

  * @Description: TODO

  * @author Comsys-Yan Fugen

  * @date Sep 13, 2016 3:29:31 PM

  *
 */
@Entity
@Table(name = "NONSTANDARDPROPERTY")
public class NonstandardProperty extends Property {

	private String name;
}
