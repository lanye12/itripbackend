package cn.itrip.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   
*/
public class Emp implements Serializable {
        //
        private Integer id;
        //
        private String name;
        //
        private Double money;
        //
        private String mark;
        //get set 方法
            public void setId (Integer  id){
                this.id=id;
            }
            public  Integer getId(){
                return this.id;
            }
            public void setName (String  name){
                this.name=name;
            }
            public  String getName(){
                return this.name;
            }
            public void setMoney (Double  money){
                this.money=money;
            }
            public  Double getMoney(){
                return this.money;
            }
            public void setMark (String  mark){
                this.mark=mark;
            }
            public  String getMark(){
                return this.mark;
            }
}
