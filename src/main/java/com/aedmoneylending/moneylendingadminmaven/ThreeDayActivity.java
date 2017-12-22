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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author REUBEN
 */
public class ThreeDayActivity extends javax.swing.JFrame {
     
    /**
     * Creates new form ThreeDayActivity
     */
    
    ArrayList<CloudModelClass> threedayDetails = new ArrayList<>();
    DatabaseReference refchild;
    final static int NO_OF_COLUMNS = 33;
    
    public ThreeDayActivity() {
        initComponents();
        super.setTitle("AeD Money Lending Dashboard [Three Day Due Loans]");
        super.setExtendedState(MAXIMIZED_BOTH);
        FirebaseCredentials();
        FirebaseRequest();
        
    }
    
    
    
     private void FirebaseRequest(){
        try{
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                threedayDetails.clear();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                         for (DataSnapshot childSnapshot: snapshot.getChildren()){
                             refchild = FirebaseDatabase.getInstance().getReference(childSnapshot.getKey()+"/User_Details");
                             refchild.orderByChild("status").equalTo("Disbursed").addChildEventListener(new ChildEventListener() {

                                 @Override
                                 public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                     CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);
                                     Calendar c  = Calendar.getInstance();
                                     //CHECKING DATE HERE
                                     
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                        c.add(Calendar.DATE,3);
                                        Date resultdate = new Date(c.getTimeInMillis());
                                        String threeday_today = sdf.format(resultdate);
                                        if(cmc.getdue_date().equals(threeday_today)){
                                            threedayDetails.add(cmc);
                                            addRowToTable();
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
    
            DefaultTableModel model = (DefaultTableModel) threedayCustDetailsTable.getModel();
            Object rowData[] = new Object[NO_OF_COLUMNS];
            
            System.out.println("Table arraylist size "+threedayDetails.size());
            for(int x =0; x<threedayDetails.size();x++)
            {
                
                rowData[0]= x+1;
                rowData[1]= threedayDetails.get(x).getFname();
                rowData[2]= threedayDetails.get(x).getMname();
                rowData[3]= threedayDetails.get(x).getLname();
                rowData[4]= threedayDetails.get(x).getPhone();
                rowData[5]= threedayDetails.get(x).getEmail();
                rowData[6]= threedayDetails.get(x).getAmount();
                rowData[7]= threedayDetails.get(x).getActual_amount();
                rowData[8]= threedayDetails.get(x).getDays();
                rowData[9]= threedayDetails.get(x).getAlternate_Phone_Number();
                rowData[10]= threedayDetails.get(x).getGender();
                rowData[11]= threedayDetails.get(x).getDate_Of_Birth();
                rowData[12]= threedayDetails.get(x).getId_Type();
                rowData[13]= threedayDetails.get(x).getId_Number();
                rowData[14]= threedayDetails.get(x).getLocation();
                rowData[15]= threedayDetails.get(x).getPost_Address();
                rowData[16]= threedayDetails.get(x).getLength_of_Res();
                rowData[17]= threedayDetails.get(x).getMari_stat();
                rowData[18]= threedayDetails.get(x).getCompany_Name();
                rowData[19]= threedayDetails.get(x).getJobTitle();
                rowData[20]= threedayDetails.get(x).getWrkPeriod();
                rowData[21]= threedayDetails.get(x).getSalary();
                rowData[22]= threedayDetails.get(x).getLoan_Purpose();
                rowData[23]= threedayDetails.get(x).getBank();
                rowData[24]= threedayDetails.get(x).getAccount_Name();
                rowData[25]= threedayDetails.get(x).getAccount_Number();
                rowData[26]= threedayDetails.get(x).getBankBranch();
                rowData[27]= threedayDetails.get(x).getGuarantor_Full_Name();
                rowData[28]= threedayDetails.get(x).getGuarantor_Date_Of_Birth();
                rowData[29]= threedayDetails.get(x).getGuarantor_Phone_Number();
                rowData[30]= threedayDetails.get(x).getdue_date();
                rowData[31]= threedayDetails.get(x).getstatus();
                rowData[32]= threedayDetails.get(x).getToken();
                                
            }
            model.addRow(rowData);
            setComboCol();
            
    }
    
    private void setComboCol() {
        TableColumn comboboxColumn = threedayCustDetailsTable.getColumnModel().getColumn(31);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Pending");
        comboBox.addItem("Denied");
        comboBox.addItem("Disbursed");
        comboBox.addItem("Outstanding");
        comboBox.addItem("Withdrawn");
        comboBox.addItem("Paid-On-Time");
        comboBox.addItem("Delayed-Repayment");
        comboboxColumn.setCellEditor(new DefaultCellEditor(comboBox));
 
}
    
    public void addSearchRowToTable(){
                
            DefaultTableModel model = (DefaultTableModel) threedayCustDetailsTable.getModel();
            //REMOVING ALL EXISTING ROWS
            if (threedayCustDetailsTable.getRowCount() > 0) {
                for (int i = threedayCustDetailsTable.getRowCount() - 1; i > -1; i--) {
                    model.removeRow(i);
                }
            }
            
            Object rowData[] = new Object[NO_OF_COLUMNS];
            
            System.out.println("Table arraylist size "+threedayDetails.size());
            for(int x =0; x<threedayDetails.size();x++)
            {
                
                rowData[0]= x+1;
                rowData[1]= threedayDetails.get(x).getFname();
                rowData[2]= threedayDetails.get(x).getMname();
                rowData[3]= threedayDetails.get(x).getLname();
                rowData[4]= threedayDetails.get(x).getPhone();
                rowData[5]= threedayDetails.get(x).getEmail();
                rowData[6]= threedayDetails.get(x).getAmount();
                rowData[7]= threedayDetails.get(x).getActual_amount();
                rowData[8]= threedayDetails.get(x).getDays();
                rowData[9]= threedayDetails.get(x).getAlternate_Phone_Number();
                rowData[10]= threedayDetails.get(x).getGender();
                rowData[11]= threedayDetails.get(x).getDate_Of_Birth();
                rowData[12]= threedayDetails.get(x).getId_Type();
                rowData[13]= threedayDetails.get(x).getId_Number();
                rowData[14]= threedayDetails.get(x).getLocation();
                rowData[15]= threedayDetails.get(x).getPost_Address();
                rowData[16]= threedayDetails.get(x).getLength_of_Res();
                rowData[17]= threedayDetails.get(x).getMari_stat();
                rowData[18]= threedayDetails.get(x).getCompany_Name();
                rowData[19]= threedayDetails.get(x).getJobTitle();
                rowData[20]= threedayDetails.get(x).getWrkPeriod();
                rowData[21]= threedayDetails.get(x).getSalary();
                rowData[22]= threedayDetails.get(x).getLoan_Purpose();
                rowData[23]= threedayDetails.get(x).getBank();
                rowData[24]= threedayDetails.get(x).getAccount_Name();
                rowData[25]= threedayDetails.get(x).getAccount_Number();
                rowData[26]= threedayDetails.get(x).getBankBranch();
                rowData[27]= threedayDetails.get(x).getGuarantor_Full_Name();
                rowData[28]= threedayDetails.get(x).getGuarantor_Date_Of_Birth();
                rowData[29]= threedayDetails.get(x).getGuarantor_Phone_Number();
                rowData[30]= threedayDetails.get(x).getdue_date();
                rowData[31]= threedayDetails.get(x).getstatus();
                rowData[32]= threedayDetails.get(x).getToken();
                //WHEN SEARCHING MODEL MUST BE HERE BCOS FIREBASE ASYNC NATURE
                model.addRow(rowData);             
            }
            
            setComboCol();
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        threedayCustDetailsTable = new javax.swing.JTable();
        searchBtn = new javax.swing.JButton();
        searchFld = new javax.swing.JTextField();
        searchCriteriaCbx = new javax.swing.JComboBox();
        viewAllBtn = new javax.swing.JButton();
        resultLbl = new javax.swing.JLabel();
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

        threedayCustDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        threedayCustDetailsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        threedayCustDetailsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        threedayCustDetailsTable.setShowHorizontalLines(false);
        threedayCustDetailsTable.setShowVerticalLines(false);
        jScrollPane1.setViewportView(threedayCustDetailsTable);
        if (threedayCustDetailsTable.getColumnModel().getColumnCount() > 0) {
            threedayCustDetailsTable.getColumnModel().getColumn(1).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(2).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(3).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(4).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(5).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(6).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(6).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(7).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(7).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(8).setMinWidth(120);
            threedayCustDetailsTable.getColumnModel().getColumn(8).setPreferredWidth(120);
            threedayCustDetailsTable.getColumnModel().getColumn(9).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(9).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(10).setMinWidth(120);
            threedayCustDetailsTable.getColumnModel().getColumn(10).setPreferredWidth(120);
            threedayCustDetailsTable.getColumnModel().getColumn(11).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(11).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(12).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(12).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(13).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(13).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(14).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(15).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(15).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(16).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(16).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(17).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(17).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(18).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(18).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(19).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(19).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(20).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(20).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(21).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(21).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(22).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(22).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(23).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(23).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(24).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(24).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(25).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(25).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(26).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(26).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(27).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(27).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(28).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(28).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(29).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(29).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(30).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(30).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(31).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(31).setPreferredWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(32).setMinWidth(150);
            threedayCustDetailsTable.getColumnModel().getColumn(32).setPreferredWidth(150);
        }

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

        searchCriteriaCbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Email", "First Name", "Last Name", "ID Number" }));
        searchCriteriaCbx.setToolTipText("Search based on this criteria");
        searchCriteriaCbx.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchCriteriaCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCriteriaCbxActionPerformed(evt);
            }
        });

        viewAllBtn.setText("View All");
        viewAllBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllBtnActionPerformed(evt);
            }
        });

        resultLbl.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N

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
                .addGap(195, 195, 195)
                .addComponent(resultLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(searchBtn)
                            .addGap(18, 18, 18)
                            .addComponent(searchFld, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(searchCriteriaCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewAllBtn)
                            .addGap(9, 9, 9))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(27, 27, 27)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(resultLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchBtn)
                        .addComponent(searchFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchCriteriaCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewAllBtn))
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addGap(33, 33, 33)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        if(searchFld.getText().isEmpty()){
            return;
        }
        threedayDetails.clear();

        //SEARCHING WITH EMAIL
        if(searchCriteriaCbx.getSelectedIndex()==0){
            String userId = searchFld.getText().replace("@", "at");
            userId = userId.replace(".", "dot");
            DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference(userId+"/User_Details");
            searchRef.orderByChild("status").equalTo("Disbursed").addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                    CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);

                    Calendar c  = Calendar.getInstance();
                    //                    //CHECKING DATE HERE
                    //
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    c.add(Calendar.DATE,3);
                    Date resultdate = new Date(c.getTimeInMillis());
                    String threeday_today = sdf.format(resultdate);
                    if(cmc.getdue_date().equals(threeday_today)){
                        threedayDetails.add(cmc);
                        addSearchRowToTable();
                    }

                    resultLbl.setText("Showing results for: "+searchFld.getText());
                    searchFld.setText("");
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
                        searchRefChild.orderByChild("status").equalTo("Disbursed").addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);

                                Calendar c  = Calendar.getInstance();
                                //                      CHECKING DATE HERE

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                c.add(Calendar.DATE,3);
                                Date resultdate = new Date(c.getTimeInMillis());
                                String threeday_today = sdf.format(resultdate);

                                //FILTERING FOR SEARCHED VAL
                                if(cmc.getFname().equals(searchFld.getText().trim()) && cmc.getdue_date().equals(threeday_today)){
                                    threedayDetails.add(cmc);
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
                        searchRefChild.orderByChild("status").equalTo("Disbursed").addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);

                                Calendar c  = Calendar.getInstance();
                                //                     CHECKING DATE HERE

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                c.add(Calendar.DATE,3);
                                Date resultdate = new Date(c.getTimeInMillis());
                                String threeday_today = sdf.format(resultdate);

                                //FILTERING FOR SEARCHED VAL
                                if(cmc.getLname().equals(searchFld.getText().trim()) && cmc.getdue_date().equals(threeday_today)){
                                    threedayDetails.add(cmc);
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
                        searchRefChild.orderByChild("status").equalTo("Disbursed").addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                                CloudModelClass cmc = snapshot.getValue(CloudModelClass.class);

                                Calendar c  = Calendar.getInstance();
                                //                     CHECKING DATE HERE

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                c.add(Calendar.DATE,3);
                                Date resultdate = new Date(c.getTimeInMillis());
                                String threeday_today = sdf.format(resultdate);

                                //FILTERING FOR SEARCHED VAL
                                if(cmc.getId_Number().equals(searchFld.getText().trim()) && cmc.getdue_date().equals(threeday_today)){
                                    threedayDetails.add(cmc);
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

    private void searchFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFldActionPerformed

    private void searchCriteriaCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCriteriaCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCriteriaCbxActionPerformed

    private void viewAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllBtnActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) threedayCustDetailsTable.getModel();
        //REMOVING ALL EXISTING ROWS
        if (threedayCustDetailsTable.getRowCount() > 0) {
            for (int i = threedayCustDetailsTable.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        FirebaseRequest();
        resultLbl.setText("");
    }//GEN-LAST:event_viewAllBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ThreeDayActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThreeDayActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThreeDayActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThreeDayActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThreeDayActivity().setVisible(true);
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
    private javax.swing.JMenuItem pendingMitem;
    private javax.swing.JLabel resultLbl;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox searchCriteriaCbx;
    private javax.swing.JTextField searchFld;
    private javax.swing.JMenuItem threeDayMitem;
    private javax.swing.JTable threedayCustDetailsTable;
    private javax.swing.JButton viewAllBtn;
    private javax.swing.JMenuItem viewAllMitem;
    // End of variables declaration//GEN-END:variables
}
