package com.salazart.db;

import java.sql.SQLException;

import com.salazart.db.models.Target;
import com.salazart.db.services.TargetService;

public class UseTarget {
    private static long socialId = 292243967;
    public static void main(String[] args) {

    TargetService targetService = new TargetService();
	try {
		targetService.insert(new Target(socialId, false));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//targetService.printAllTargets();
    //for (int i = 100000; i < 159041; i++) {
    	//targetService.updateStatusTargetById(i, false);
    	//System.out.println(i);
	//}
    
    /*List<Long> ids = targetService.getAllIds();
    for (int i = 292000; i < ids.size(); i++) {
    	System.out.println(i);
		Target target = targetService.getTargetById(ids.get(i));
		System.out.println(target.getSocialId() +  " " + target.getStatusTarget());
		targetService.insertTarget2(target);
	}*/
    //targetService.execeteQuery(query);
    //targetService.deleteDublicates();
	//targetService.printAllTargets();
	
    }

}
