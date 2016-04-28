package com.importsource.util;

public class Test {
   public static void main(String[] args){
	  // Object x = new Integer(0);
	  String sql= "SELECT "+
		"entity_type entityType,"+
		
		"note note "+
	"FROM "+ 
		"util_audit_log "+ 
	"WHERE"+ 
		" 1 = 1"+
		"#{not_nvl(type,' AND entity_type = '+bind('type'))}"+
		"#{not_nvl(keyword,' AND ( operator like ' + bind('keyword') +' OR operation like '+bind('keyword')+' OR client like '+bind('keyword')+' OR note like '+bind('keyword')+')')}"+
	   " #{not_nvl(id,' AND entity_id = '+bind('id'))}"+
	  "  #{not_nvl(startDate,' AND create_date  &gt;= from_unixtime('+bind('startDate')+'/1000)')}"+
	   " #{not_nvl(endDate,' AND create_date &lt;= from_unixtime('+bind('endDate') + '/1000)')}	"+				        
	"ORDER BY "+
	  " 	create_date"+  
	" DESC "+
      	"#{not_nvl(offset ,'limit ' + offset + not_nvl(limit,',' + limit))}";
	  String[] sqls=sql.split("[#]");
	  for(int i=0;i<sqls.length;i++){
		  System.out.println(sqls[i]);
		  System.out.println("---------------------------------------------------------------");
	  }
	//   System.out.println((String)x);
   }
}
