/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aedmoneylending.moneylendingadminmaven;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author REUBEN
 */
public class allCustDetailsTableModel extends AbstractTableModel{
  Object getAllCust[][];
  String tabTitle[] = {"No", "FIRST NAME","MIDDLE NAME","LAST NAME","PHONE NUM","EMAIL","AMOUNT(with interest)","AMOUNT(without interest)","DAYS",
      "ALT PHONE NUM","GENDER","DATE OF BIRTH","ID TYPE","ID NUMBER","LOCATION","POST ADDR",
      "LENGTH OF RES(yrs)","MARITAL STATUS","COMPANY NAME","JOB TITLE","WORK PERIOD","SALARY",
      "LOAN PURPOSE","BANK NAME","ACCOUNT NAME","ACCOUNT NUMBER","BANK BRANCH","GUARANTOR FULL NAME"
     ,"GUARANTOR DOB","GUARANTOR PHONE NUM","DUE DATE","STATUS","CUSTOMER ID"};  
    
    public static Vector allCustVec;
    final static int NO_OF_COLUMNS_LESS_ONE = 32;
    List<CloudModelClass> allUserDetails = new ArrayList<>();

    public allCustDetailsTableModel() {
        try{
            FileInputStream serviceAccount = new FileInputStream("C:\\java_projects\\moneylendingadminmaven\\flashchatandroid-f5fa2-firebase-adminsdk-h03ad-1844424337.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl("https://flashchatandroid-f5fa2.firebaseio.com").build();
            FirebaseApp.initializeApp(options);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   
                    allUserDetails.clear();
                   
                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                        CloudModelClass cmc = childSnapshot.getValue(CloudModelClass.class);
                        allUserDetails.add(cmc);
                        System.err.println("Names "+allUserDetails.get(0));
                        //System.err.println("Data of array list is "+allUserDetails.toString());
                    }
                    
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            getAllCust = new Object[allCustVec.size()/NO_OF_COLUMNS_LESS_ONE][tabTitle.length];
                    int rowSetter = 0;
                    int y = 0;
                    for(int x =0; x<allUserDetails.size();x++)
                    {
                        if(y == 0)
                            getAllCust[rowSetter][y] = rowSetter+1;

                        y++;
                        getAllCust[rowSetter][y] =allUserDetails.get(x);


                        if(y==NO_OF_COLUMNS_LESS_ONE)
                        {
                            y = 0;
                            rowSetter++;
                        }

                    }
                    for(int x = 0; x<allUserDetails.size();x++){
                        System.out.println("Data is "+allUserDetails.get(x));
                    }
        }
        catch(Exception e){
        }
    }
    
    
    
        
    @Override
    public int getRowCount() {
       return getAllCust.length;
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return tabTitle.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return getAllCust[rowIndex][columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int row , int col)
    {
        return false;
    }
    
    @Override
    public String getColumnName(int column)
    {
        return tabTitle[column];
    }
    
    @Override
    public void setValueAt(Object value,int row,int col)
    {
        getAllCust[row][col]=value;
    }
    
    
}
