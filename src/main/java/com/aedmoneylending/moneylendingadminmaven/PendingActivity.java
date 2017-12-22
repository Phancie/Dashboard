/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aedmoneylending.moneylendingadminmaven;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author REUBEN
 */
public class PendingActivity extends javax.swing.JFrame implements TableModelListener {

    /**
     * Creates new form PendingActivity
     */
    ArrayList<CloudModelClass> pendingUserDetails = new ArrayList<>();
    DatabaseReference refchild;
    final static int NO_OF_COLUMNS = 33;
    static String key;
    int flag = 0;
    String updateEmail = null;
    
    public PendingActivity() {
        initComponents();
        super.setTitle("AeD Money Lending Dashboard [Pending Loans]");
        super.setExtendedState(MAXIMIZED_BOTH);
        FirebaseCredentials();
        FirebaseRequest();
    }

    
    private void FirebaseRequest(){
        try{
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                pendingUserDetails.clear();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                         for (DataSnapshot childSnapshot: snapshot.getChildren()){
                             refchild = FirebaseDatabase.getInstance().getReference(childSnapshot.getKey()+"/User_Details");
                             refchild.orderByChild("status").equalTo("Pending").addChildEventListener(new ChildEventListener() {

                                 @Override
                                 public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                     CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);
                                     pendingUserDetails.add(cmc);
                                     addRowToTable();
                                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }

                                 @Override
                                 public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }

                                 @Override
                                 public void onChildRemoved(DataSnapshot snapshot) {
                                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }

                                 @Override
                                 public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }

                                 @Override
                                 public void onCancelled(DatabaseError error) {
                                     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }
                             });
                         
                         }
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Network Error","Please check network connections",-1);
        }
    }
    
    private void FirebaseCredentials(){
            try{
                FileInputStream serviceAccount = new FileInputStream("C:\\java_projects\\moneylendingadminmaven\\flashchatandroid-f5fa2-firebase-adminsdk-h03ad-1844424337.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                        .setDatabaseUrl("https://flashchatandroid-f5fa2.firebaseio.com").build();
                FirebaseApp.initializeApp(options);
            }
            catch(Exception e){
                
            }
        }
    
    public void addRowToTable(){
    
            DefaultTableModel model = (DefaultTableModel) pendingCustDetailsTable.getModel();
            Object rowData[] = new Object[NO_OF_COLUMNS];
            
            System.out.println("Table arraylist size "+pendingUserDetails.size());
            for(int x =0; x<pendingUserDetails.size();x++)
            {
                
                rowData[0]= x+1;
                rowData[1]= pendingUserDetails.get(x).getFname();
                rowData[2]= pendingUserDetails.get(x).getMname();
                rowData[3]= pendingUserDetails.get(x).getLname();
                rowData[4]= pendingUserDetails.get(x).getPhone();
                rowData[5]= pendingUserDetails.get(x).getEmail();
                rowData[6]= pendingUserDetails.get(x).getAmount();
                rowData[7]= pendingUserDetails.get(x).getActual_amount();
                rowData[8]= pendingUserDetails.get(x).getDays();
                rowData[9]= pendingUserDetails.get(x).getAlternate_Phone_Number();
                rowData[10]= pendingUserDetails.get(x).getGender();
                rowData[11]= pendingUserDetails.get(x).getDate_Of_Birth();
                rowData[12]= pendingUserDetails.get(x).getId_Type();
                rowData[13]= pendingUserDetails.get(x).getId_Number();
                rowData[14]= pendingUserDetails.get(x).getLocation();
                rowData[15]= pendingUserDetails.get(x).getPost_Address();
                rowData[16]= pendingUserDetails.get(x).getLength_of_Res();
                rowData[17]= pendingUserDetails.get(x).getMari_stat();
                rowData[18]= pendingUserDetails.get(x).getCompany_Name();
                rowData[19]= pendingUserDetails.get(x).getJobTitle();
                rowData[20]= pendingUserDetails.get(x).getWrkPeriod();
                rowData[21]= pendingUserDetails.get(x).getSalary();
                rowData[22]= pendingUserDetails.get(x).getLoan_Purpose();
                rowData[23]= pendingUserDetails.get(x).getBank();
                rowData[24]= pendingUserDetails.get(x).getAccount_Name();
                rowData[25]= pendingUserDetails.get(x).getAccount_Number();
                rowData[26]= pendingUserDetails.get(x).getBankBranch();
                rowData[27]= pendingUserDetails.get(x).getGuarantor_Full_Name();
                rowData[28]= pendingUserDetails.get(x).getGuarantor_Date_Of_Birth();
                rowData[29]= pendingUserDetails.get(x).getGuarantor_Phone_Number();
                rowData[30]= pendingUserDetails.get(x).getdue_date();
                rowData[31]= pendingUserDetails.get(x).getstatus();
                rowData[32]= pendingUserDetails.get(x).getToken();
                                
            }
            model.addRow(rowData);
            setComboCol();
            
    }
    
    private void setComboCol() {
        TableColumn comboboxColumn = pendingCustDetailsTable.getColumnModel().getColumn(31);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Pending");
        comboBox.addItem("Approved");
        comboBox.addItem("Denied");
        comboBox.addItem("Disbursed");
        comboBox.addItem("Outstanding");
        comboBox.addItem("Withdrawn");
        comboBox.addItem("Paid-On-Time");
        comboBox.addItem("Delayed-Repayment");
        comboboxColumn.setCellEditor(new DefaultCellEditor(comboBox));
 
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    private  void updateStatus(String value,int r,int c){
        
        String userId = updateEmail.replace("@", "at");
                userId = userId.replace(".", "dot");
                System.err.println("User id "+userId);
                final String id = userId;
                final String val = value;
                final int row = r;
                final int col = c;
                final DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference(userId+"/User_Details");
                DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference(userId+"/User_Details");
                searchRef.addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);
                        key = snapshot.getKey();
                        String requestedDays = cmc.getDays();
                        
                           
                        //UPDATING FIELDS
                         if(val.equals("Disbursed") || val.equals("Outstanding")){
                            int convertDays = Integer.parseInt(requestedDays);
                            Calendar c  = Calendar.getInstance();
                            //CALCULATING DUE DATE HERE

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            c.add(Calendar.DATE,convertDays);
                            Date resultdate = new Date(c.getTimeInMillis());
                            String approvedDueDate = sdf.format(resultdate);
                            updateRef.child(key).child("status").setValue(val);
                            updateRef.child(key).child("due_date").setValue(approvedDueDate);
                            Object dateObj = (Object)approvedDueDate;
                            pendingCustDetailsTable.setValueAt(dateObj, row, col);
                        }
                        if(val.equals("Pending") || val.equals("Withdrawn") || val.equals("Approved") || val.equals("Denied")){
                            updateRef.child(key).child("status").setValue(val);
                            updateRef.child(key).child("due_date").setValue("Unspecificied");
                            pendingCustDetailsTable.setValueAt("Unspecificied", row, col);
                        }
                        if(val.equals("Paid-On-Time")){
                            Calendar c  = Calendar.getInstance();
                            //CALCULATING DUE DATE HERE
                            int convertDays = Integer.parseInt(requestedDays);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            c.add(Calendar.DATE,convertDays);
                            Date resultdate = new Date(c.getTimeInMillis());
                            String approvedDueDate = sdf.format(resultdate);
                            updateRef.child(key).child("status").setValue(val);
                            Object dateObj = (Object)approvedDueDate;
                            pendingCustDetailsTable.setValueAt(dateObj, row, col);
                        }
                        if(val.equals("Delayed-Repayment")){
                            int convertDays = Integer.parseInt(requestedDays);
                            Calendar c  = Calendar.getInstance();
                            //CALCULATING DUE DATE HERE

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            c.add(Calendar.DATE,convertDays);
                            Date resultdate = new Date(c.getTimeInMillis());
                            String approvedDueDate = sdf.format(resultdate);
                            updateRef.child(key).child("status").setValue(val);
                            Object dateObj = (Object)approvedDueDate;
                            pendingCustDetailsTable.setValueAt(dateObj, row, col);
                        }
                        
                        flag = 0;
                    }

                    @Override
                    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot snapshot) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBtn = new javax.swing.JButton();
        searchFld = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pendingCustDetailsTable = new javax.swing.JTable();
        searchCriteriaCbx = new javax.swing.JComboBox();
        resultLbl = new javax.swing.JLabel();
        viewAllBtn = new javax.swing.JButton();
        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        pendingMitem = new javax.swing.JMenuItem();
        approvedMitem = new javax.swing.JMenuItem();
        disbursedMitem = new javax.swing.JMenuItem();
        dueMitem = new javax.swing.JMenuItem();
        viewAllMitem = new javax.swing.JMenuItem();
        Events = new javax.swing.JMenu();
        onedayMitem = new javax.swing.JMenuItem();
        threeDayMitem = new javax.swing.JMenuItem();
        fiveDayMitem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(801, 750));

        searchBtn.setText("Search");
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        searchFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFldActionPerformed(evt);
            }
        });

        pendingCustDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "FIRST NAME", "MIDDLE NAME", "LAST NAME", "PHONE NUM", "EMAIL", "AMOUNT(with interest)GHC", "AMOUNT(without interest)GHC", "DAYS", "ALT PHONE NUM", "GENDER", "DATE OF BIRTH", "ID TYPE", "ID NUMBER", "LOCATION", "P.O. BOX", "LENGTH OF RESIDENCE", "MARITAL STATUS", "COMPANY NAME", "JOB TITLE", "WORK PERIOD", "SALARY", "LOAN PURPOSE", "BANK  NAME", "ACCOUNT NAME", "ACCOUNT NUMBER", "BANK BRANCH", "GUARANTOR NAME", "GUARANTOR DOB", "GUARANTOR PHONE NUM", "DUE DATE", "STATUS", "CUSTOMER TOKEN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pendingCustDetailsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        pendingCustDetailsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pendingCustDetailsTable.setShowHorizontalLines(false);
        pendingCustDetailsTable.setShowVerticalLines(false);
        jScrollPane1.setViewportView(pendingCustDetailsTable);
        if (pendingCustDetailsTable.getColumnModel().getColumnCount() > 0) {
            pendingCustDetailsTable.getColumnModel().getColumn(0).setPreferredWidth(70);
            pendingCustDetailsTable.getColumnModel().getColumn(1).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(2).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(3).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(4).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(6).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(6).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(7).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(7).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(8).setMinWidth(130);
            pendingCustDetailsTable.getColumnModel().getColumn(8).setPreferredWidth(130);
            pendingCustDetailsTable.getColumnModel().getColumn(9).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(9).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(10).setResizable(false);
            pendingCustDetailsTable.getColumnModel().getColumn(10).setPreferredWidth(100);
            pendingCustDetailsTable.getColumnModel().getColumn(11).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(11).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(12).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(12).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(13).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(13).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(14).setMinWidth(170);
            pendingCustDetailsTable.getColumnModel().getColumn(14).setPreferredWidth(170);
            pendingCustDetailsTable.getColumnModel().getColumn(15).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(15).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(16).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(16).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(17).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(17).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(18).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(18).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(19).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(19).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(20).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(20).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(21).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(21).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(22).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(22).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(23).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(23).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(24).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(24).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(25).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(25).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(26).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(26).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(27).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(27).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(28).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(28).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(29).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(29).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(30).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(30).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(31).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(31).setPreferredWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(32).setMinWidth(150);
            pendingCustDetailsTable.getColumnModel().getColumn(32).setPreferredWidth(150);
        }

        searchCriteriaCbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Email", "First Name", "Last Name", "ID Number" }));
        searchCriteriaCbx.setToolTipText("Search based on this criteria");
        searchCriteriaCbx.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchCriteriaCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCriteriaCbxActionPerformed(evt);
            }
        });

        resultLbl.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N

        viewAllBtn.setText("View All");
        viewAllBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllBtnActionPerformed(evt);
            }
        });

        fileMenu.setText("File");
        fileMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        pendingMitem.setText("Pending Loans");
        pendingMitem.setToolTipText("Loans which are still under consideration");
        pendingMitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingMitemActionPerformed(evt);
            }
        });
        fileMenu.add(pendingMitem);

        approvedMitem.setText("Approved Loans");
        approvedMitem.setToolTipText("Loans that have been considered");
        approvedMitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approvedMitemActionPerformed(evt);
            }
        });
        fileMenu.add(approvedMitem);

        disbursedMitem.setText("Disbursed Loans");
        disbursedMitem.setToolTipText("Loans have been given out but not ready for repayment");
        disbursedMitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disbursedMitemActionPerformed(evt);
            }
        });
        fileMenu.add(disbursedMitem);

        dueMitem.setText("Due Loans");
        dueMitem.setToolTipText("Loans which are ready to be paid back");
        dueMitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueMitemActionPerformed(evt);
            }
        });
        fileMenu.add(dueMitem);

        viewAllMitem.setText("View All");
        viewAllMitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllMitemActionPerformed(evt);
            }
        });
        fileMenu.add(viewAllMitem);

        mainMenu.add(fileMenu);

        Events.setText("Events");
        Events.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        onedayMitem.setText("One Day Due Loans");
        onedayMitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onedayMitemActionPerformed(evt);
            }
        });
        Events.add(onedayMitem);

        threeDayMitem.setText("Three Day Due Loans");
        threeDayMitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeDayMitemActionPerformed(evt);
            }
        });
        Events.add(threeDayMitem);

        fiveDayMitem.setText("Five Day Due Loans");
        fiveDayMitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiveDayMitemActionPerformed(evt);
            }
        });
        Events.add(fiveDayMitem);

        mainMenu.add(Events);

        setJMenuBar(mainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(searchBtn)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchFld, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(searchCriteriaCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(resultLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(viewAllBtn)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchFld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchBtn)
                        .addComponent(searchCriteriaCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewAllBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFldActionPerformed

    private void searchCriteriaCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCriteriaCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCriteriaCbxActionPerformed

    private void viewAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllBtnActionPerformed
        // TODO add your handling code here:
        
         DefaultTableModel model = (DefaultTableModel) pendingCustDetailsTable.getModel();
        //REMOVING ALL EXISTING ROWS
            if (pendingCustDetailsTable.getRowCount() > 0) {
                for (int i = pendingCustDetailsTable.getRowCount() - 1; i > -1; i--) {
                    model.removeRow(i);
                }
            }
        FirebaseRequest();
        resultLbl.setText("");
    }//GEN-LAST:event_viewAllBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        if(searchFld.getText().isEmpty()){
            return;
        }
        pendingUserDetails.clear();
        
        //SEARCHING WITH EMAIL
        if(searchCriteriaCbx.getSelectedIndex()==0){
                String userId = searchFld.getText().replace("@", "at");
                userId = userId.replace(".", "dot");
                DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference(userId+"/User_Details");
                searchRef.orderByChild("status").equalTo("Pending").addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                        CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);
                        pendingUserDetails.add(cmc);
                        
                         addSearchRowToTable();
                         resultLbl.setText("Showing results for: "+searchFld.getText());
                         updateEmail = searchFld.getText().trim();
                         searchFld.setText("");
                         flag = 1;
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot snapshot) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
        }
        
        //SEARCH BY FIRST NAME
        if(searchCriteriaCbx.getSelectedIndex()==1){
            
            DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference();
                  searchRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                        DatabaseReference searchRefChild = FirebaseDatabase.getInstance().getReference(childSnapshot.getKey()+"/User_Details");
                        searchRefChild.orderByChild("status").equalTo("Pending").addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);
                                    //FILTERING FOR SEARCHED VAL
                                    if(cmc.getFname().equals(searchFld.getText().trim())){
                                        pendingUserDetails.add(cmc);
                                        addSearchRowToTable();
                                        
                                        resultLbl.setText("Showing results for: "+searchFld.getText());
                                        searchFld.setText("");
                                    }
                                    
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildRemoved(DataSnapshot snapshot) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        
        //SEARCH BY LAST NAME
        if(searchCriteriaCbx.getSelectedIndex()==2){
            
            DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference();
                  searchRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                        DatabaseReference searchRefChild = FirebaseDatabase.getInstance().getReference(childSnapshot.getKey()+"/User_Details");
                        searchRefChild.orderByChild("status").equalTo("Pending").addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);
                                    //FILTERING FOR SEARCHED VAL
                                    if(cmc.getLname().equals(searchFld.getText().trim())){
                                        pendingUserDetails.add(cmc);
                                        addSearchRowToTable();
                                        resultLbl.setText("Showing results for: "+searchFld.getText());
                                        searchFld.setText("");
                                    }
                                    
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildRemoved(DataSnapshot snapshot) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        
        //SEARCH BY ID NUMBER
        if(searchCriteriaCbx.getSelectedIndex()==3){
            
            DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference();
                  searchRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                        DatabaseReference searchRefChild = FirebaseDatabase.getInstance().getReference(childSnapshot.getKey()+"/User_Details");
                        searchRefChild.orderByChild("status").equalTo("Pending").addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);
                                    //FILTERING FOR SEARCHED VAL
                                    if(cmc.getId_Number().equals(searchFld.getText().trim())){
                                        pendingUserDetails.add(cmc);
                                        addSearchRowToTable();
                                        resultLbl.setText("Showing results for: "+searchFld.getText());
                                        searchFld.setText("");
                                    }
                                    
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildRemoved(DataSnapshot snapshot) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void pendingMitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingMitemActionPerformed
        // TODO add your handling code here:

        ApprovedActivity aa = new ApprovedActivity();
        aa.setVisible(false);
        DisbursedActivity dda = new DisbursedActivity();
        dda.setVisible(false);
        DueActivity da = new DueActivity();
        da.setVisible(false);
        MainActivity ma = new MainActivity();
        ma.setVisible(false);
        OneDayActivity oa = new OneDayActivity();
        oa.setVisible(false);
        PendingActivity pa = new PendingActivity();
        pa.setVisible(true);
        ThreeDayActivity ta = new ThreeDayActivity();
        ta.setVisible(false);
        WeekDayActivity wa = new WeekDayActivity();
        wa.setVisible(false);

    }//GEN-LAST:event_pendingMitemActionPerformed

    private void approvedMitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approvedMitemActionPerformed
        // TODO add your handling code here:
        ApprovedActivity aa = new ApprovedActivity();
        aa.setVisible(true);
        DisbursedActivity dda = new DisbursedActivity();
        dda.setVisible(false);
        DueActivity da = new DueActivity();
        da.setVisible(false);
        MainActivity ma = new MainActivity();
        ma.setVisible(false);
        OneDayActivity oa = new OneDayActivity();
        oa.setVisible(false);
        PendingActivity pa = new PendingActivity();
        pa.setVisible(false);
        ThreeDayActivity ta = new ThreeDayActivity();
        ta.setVisible(false);
        WeekDayActivity wa = new WeekDayActivity();
        wa.setVisible(false);
    }//GEN-LAST:event_approvedMitemActionPerformed

    private void disbursedMitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disbursedMitemActionPerformed
        // TODO add your handling code here:
        ApprovedActivity aa = new ApprovedActivity();
        aa.setVisible(false);
        DisbursedActivity dda = new DisbursedActivity();
        dda.setVisible(true);
        DueActivity da = new DueActivity();
        da.setVisible(false);
        MainActivity ma = new MainActivity();
        ma.setVisible(false);
        OneDayActivity oa = new OneDayActivity();
        oa.setVisible(false);
        PendingActivity pa = new PendingActivity();
        pa.setVisible(false);
        ThreeDayActivity ta = new ThreeDayActivity();
        ta.setVisible(false);
        WeekDayActivity wa = new WeekDayActivity();
        wa.setVisible(false);
    }//GEN-LAST:event_disbursedMitemActionPerformed

    private void dueMitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueMitemActionPerformed
        // TODO add your handling code here:
        ApprovedActivity aa = new ApprovedActivity();
        aa.setVisible(false);
        DisbursedActivity dda = new DisbursedActivity();
        dda.setVisible(false);
        DueActivity da = new DueActivity();
        da.setVisible(true);
        MainActivity ma = new MainActivity();
        ma.setVisible(false);
        OneDayActivity oa = new OneDayActivity();
        oa.setVisible(false);
        PendingActivity pa = new PendingActivity();
        pa.setVisible(false);
        ThreeDayActivity ta = new ThreeDayActivity();
        ta.setVisible(false);
        WeekDayActivity wa = new WeekDayActivity();
        wa.setVisible(false);
    }//GEN-LAST:event_dueMitemActionPerformed

    private void viewAllMitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllMitemActionPerformed
        // TODO add your handling code here:
        ApprovedActivity aa = new ApprovedActivity();
        aa.setVisible(false);
        DisbursedActivity dda = new DisbursedActivity();
        dda.setVisible(false);
        DueActivity da = new DueActivity();
        da.setVisible(false);
        MainActivity ma = new MainActivity();
        ma.setVisible(true);
        OneDayActivity oa = new OneDayActivity();
        oa.setVisible(false);
        PendingActivity pa = new PendingActivity();
        pa.setVisible(false);
        ThreeDayActivity ta = new ThreeDayActivity();
        ta.setVisible(false);
        WeekDayActivity wa = new WeekDayActivity();
        wa.setVisible(false);
    }//GEN-LAST:event_viewAllMitemActionPerformed

    private void onedayMitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onedayMitemActionPerformed
        // TODO add your handling code here:
        ApprovedActivity aa = new ApprovedActivity();
        aa.setVisible(false);
        DisbursedActivity dda = new DisbursedActivity();
        dda.setVisible(false);
        DueActivity da = new DueActivity();
        da.setVisible(false);
        MainActivity ma = new MainActivity();
        ma.setVisible(false);
        OneDayActivity oa = new OneDayActivity();
        oa.setVisible(true);
        PendingActivity pa = new PendingActivity();
        pa.setVisible(false);
        ThreeDayActivity ta = new ThreeDayActivity();
        ta.setVisible(false);
        WeekDayActivity wa = new WeekDayActivity();
        wa.setVisible(false);
    }//GEN-LAST:event_onedayMitemActionPerformed

    private void threeDayMitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeDayMitemActionPerformed
        // TODO add your handling code here:
        ApprovedActivity aa = new ApprovedActivity();
        aa.setVisible(false);
        DisbursedActivity dda = new DisbursedActivity();
        dda.setVisible(false);
        DueActivity da = new DueActivity();
        da.setVisible(false);
        MainActivity ma = new MainActivity();
        ma.setVisible(false);
        OneDayActivity oa = new OneDayActivity();
        oa.setVisible(false);
        PendingActivity pa = new PendingActivity();
        pa.setVisible(false);
        ThreeDayActivity ta = new ThreeDayActivity();
        ta.setVisible(true);
        WeekDayActivity wa = new WeekDayActivity();
        wa.setVisible(false);
    }//GEN-LAST:event_threeDayMitemActionPerformed

    private void fiveDayMitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveDayMitemActionPerformed
        // TODO add your handling code here:
        ApprovedActivity aa = new ApprovedActivity();
        aa.setVisible(false);
        DisbursedActivity dda = new DisbursedActivity();
        dda.setVisible(false);
        DueActivity da = new DueActivity();
        da.setVisible(false);
        MainActivity ma = new MainActivity();
        ma.setVisible(false);
        OneDayActivity oa = new OneDayActivity();
        oa.setVisible(false);
        PendingActivity pa = new PendingActivity();
        pa.setVisible(false);
        ThreeDayActivity ta = new ThreeDayActivity();
        ta.setVisible(false);
        WeekDayActivity wa = new WeekDayActivity();
        wa.setVisible(true);
    }//GEN-LAST:event_fiveDayMitemActionPerformed

    public void addSearchRowToTable(){
                
            DefaultTableModel model = (DefaultTableModel) pendingCustDetailsTable.getModel();
            //REMOVING ALL EXISTING ROWS
            if (pendingCustDetailsTable.getRowCount() > 0) {
                for (int i = pendingCustDetailsTable.getRowCount() - 1; i > -1; i--) {
                    model.removeRow(i);
                }
            }
            
            Object rowData[] = new Object[NO_OF_COLUMNS];
            
            System.out.println("Table arraylist size "+pendingUserDetails.size());
            for(int x =0; x<pendingUserDetails.size();x++)
            {
                
                rowData[0]= x+1;
                rowData[1]= pendingUserDetails.get(x).getFname();
                rowData[2]= pendingUserDetails.get(x).getMname();
                rowData[3]= pendingUserDetails.get(x).getLname();
                rowData[4]= pendingUserDetails.get(x).getPhone();
                rowData[5]= pendingUserDetails.get(x).getEmail();
                rowData[6]= pendingUserDetails.get(x).getAmount();
                rowData[7]= pendingUserDetails.get(x).getActual_amount();
                rowData[8]= pendingUserDetails.get(x).getDays();
                rowData[9]= pendingUserDetails.get(x).getAlternate_Phone_Number();
                rowData[10]= pendingUserDetails.get(x).getGender();
                rowData[11]= pendingUserDetails.get(x).getDate_Of_Birth();
                rowData[12]= pendingUserDetails.get(x).getId_Type();
                rowData[13]= pendingUserDetails.get(x).getId_Number();
                rowData[14]= pendingUserDetails.get(x).getLocation();
                rowData[15]= pendingUserDetails.get(x).getPost_Address();
                rowData[16]= pendingUserDetails.get(x).getLength_of_Res();
                rowData[17]= pendingUserDetails.get(x).getMari_stat();
                rowData[18]= pendingUserDetails.get(x).getCompany_Name();
                rowData[19]= pendingUserDetails.get(x).getJobTitle();
                rowData[20]= pendingUserDetails.get(x).getWrkPeriod();
                rowData[21]= pendingUserDetails.get(x).getSalary();
                rowData[22]= pendingUserDetails.get(x).getLoan_Purpose();
                rowData[23]= pendingUserDetails.get(x).getBank();
                rowData[24]= pendingUserDetails.get(x).getAccount_Name();
                rowData[25]= pendingUserDetails.get(x).getAccount_Number();
                rowData[26]= pendingUserDetails.get(x).getBankBranch();
                rowData[27]= pendingUserDetails.get(x).getGuarantor_Full_Name();
                rowData[28]= pendingUserDetails.get(x).getGuarantor_Date_Of_Birth();
                rowData[29]= pendingUserDetails.get(x).getGuarantor_Phone_Number();
                rowData[30]= pendingUserDetails.get(x).getdue_date();
                rowData[31]= pendingUserDetails.get(x).getstatus();
                rowData[32]= pendingUserDetails.get(x).getToken();
                //WHEN SEARCHING MODEL MUST BE HERE BCOS FIREBASE ASYNC NATURE
                model.addRow(rowData);             
            }
            
            setComboCol();
            
    }
    

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PendingActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PendingActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PendingActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PendingActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PendingActivity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Events;
    private javax.swing.JMenuItem approvedMitem;
    private javax.swing.JMenuItem disbursedMitem;
    private javax.swing.JMenuItem dueMitem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fiveDayMitem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenuItem onedayMitem;
    private javax.swing.JTable pendingCustDetailsTable;
    private javax.swing.JMenuItem pendingMitem;
    private javax.swing.JLabel resultLbl;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox searchCriteriaCbx;
    private javax.swing.JTextField searchFld;
    private javax.swing.JMenuItem threeDayMitem;
    private javax.swing.JButton viewAllBtn;
    private javax.swing.JMenuItem viewAllMitem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        //UPDATE INFO ONLY WHEN SEARCH IS CONDUCTED
        if(flag==1){
            DefaultTableModel model = (DefaultTableModel)e.getSource();
            //String columnName = model.getColumnName(column);
            Object data = model.getValueAt(row, column);
            System.err.println("Data change "+data);
            //COLUMN LESS ONE TO GET THE DUE DATE COL
            updateStatus(data.toString(),row,column-1);
            
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}