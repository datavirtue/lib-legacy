/*
 * ExportDialog.java
 *
 * Created on June 29, 2007, 11:10 PM
 */

package datavirtue;
import de.schlichtherle.io.*;




/**
 *
 * @author  Data Virtue
 */
public class ExportProgressDialog extends javax.swing.JDialog implements Runnable, ProgressDialogInterface{
  
    private File f; 
    private DbEngine db;
    private String dbname;
    private int [] fields;
    private boolean append = false;
    private String message = "Export - Please Wait";
    
    /** Creates new form ExportDialog */
    public ExportProgressDialog(java.awt.Frame parent, boolean modal, DbEngine db, String dbname,
            int [] fields, java.io.File f, String message, boolean append) {
    
        super(parent, false);
        initComponents();
                 
        this.db = db;
        this.dbname = dbname;
        this.f = new File(f);
        if (message != null) this.message = message;
                
        jLabel1.setText(message);
        
        this.fields = fields;
        this.append = append;
         
        jProgressBar1.setMinimum(0);
             
        java.awt.Dimension dim = DV.computeCenter((java.awt.Window) this);
        
        this.setLocation(dim.width, dim.height);
        
        this.setVisible(true);
        
        new Thread(this).start();
        
        //synchronized(this){notifyAll();}
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Export Progress");
        setAlwaysOnTop(true);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jProgressBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
      /* This class implements Runnable, the work gets don in here 
       but you need to suspend it until notification ('GO'button press)
     */
    public void run () {
        
        /*try{synchronized(this){wait();}}
            catch (InterruptedException e){}*/
        
        int a = db.csvExport(dbname, f, fields, this);
        this.dispose();        
    }
    
     /* Reach in methods to update the JProgressBar  */
    public void setBarMax(int value){
        
        jProgressBar1.setMaximum(value);
        
    }
    
    public void updateBar (int value){
        
        jProgressBar1.setValue(value);
        
    }
    
    public void close() {
        
        
        //this.dispose();
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
    
}
