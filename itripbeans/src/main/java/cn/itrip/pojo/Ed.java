package cn.itrip.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   
*/
public class Ed implements Serializable {
        //
        private Integer id;
        //
        private Integer empid;
        //
        private Integer deptid;
        //get set 方法
            public void setId (Integer  id){
                this.id=id;
            }
            public  Integer getId(){
                return this.id;
            }
            public void setEmpid (Integer  empid){
                this.empid=empid;
            }
            public  Integer getEmpid(){
                return this.empid;
            }
            public void setDeptid (Integer  deptid){
                this.deptid=deptid;
            }
            public  Integer getDeptid(){
                return this.deptid;
            }
}
