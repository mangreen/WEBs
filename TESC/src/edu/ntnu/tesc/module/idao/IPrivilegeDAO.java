package edu.ntnu.tesc.module.idao;

import java.util.List;

import edu.ntnu.tesc.module.beans.Privilege;;

public interface IPrivilegeDAO {

	public List<Privilege> getPrivilegeList();
    public Privilege getPrivilege(int autoindex);
    public int insertPrivilege(Privilege privilege);
    public int updatePrivilege(Privilege privilege);
    public int delPrivilege(int autoindex);
}
