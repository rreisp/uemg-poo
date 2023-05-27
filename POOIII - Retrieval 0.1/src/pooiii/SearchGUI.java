/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooiii;

import java.awt.Component;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author rreisp
 */
public class SearchGUI extends javax.swing.JFrame {

    String[] columnNames = new String[5];
    Object[][] data;

    /**
     * Creates new form SearchGUI
     */
    public SearchGUI() {
        initComponents();
        customizeGUI();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        btSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pesquisa:");

        btSearch.setText("Buscar");
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSearch)
                    .addComponent(btSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 402, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed
        searchButtonClicked(evt);
    }//GEN-LAST:event_btSearchActionPerformed

    public void customizeGUI() {
        int counter = 1000;

        this.columnNames[0] = "Id";
        this.columnNames[1] = "Nome";
        this.columnNames[2] = "Sobrenome";
        this.columnNames[3] = "Editar";
        this.columnNames[4] = "Remover";

        data = new Object[counter][3];
        int i = 0;
        while (i < counter) {
            String nome = "nome";
            String sobrenome = "sobrenome";
            data[i][0] = i;
            data[i][1] = nome;
            data[i][2] = sobrenome;
            i++;
        }

        // Cria e atribui dinamicamente um modelo de tabela através dos
        //  títulos e do vetor de dados.
        // Espficifica que as células neste modelo não são editáveis,
        // independentemente da linha ou coluna.
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // Determina que a ordem das colunas da tabela não pode ser alterada
        jTable1.getTableHeader().setReorderingAllowed(false);

        // Determina que a forma de renderização para as colunas com índice
        // 3 e 4 devem seguir o modelo da classe 'ButtonRenderer'
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer("Editar"));
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer("Remover"));

        // Adiciona um listener à tabela, para que os eventos de mouse sejam
        // capturados.
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableButtonClicked(evt);
            }
        });
    }
    

    // Método acionado quando ocorre um click no botão de busca
    private void searchButtonClicked(java.awt.event.ActionEvent evt) {
        String text = tfSearch.getText();
        System.out.println(text);
        JOptionPane.showMessageDialog(this, text);                        
                
        if(text.trim().length() != 0){
            StringTokenizer st = new StringTokenizer(text);
            while(st.hasMoreTokens()){
                String token = st.nextToken();
            }
        }
    }    

    // Método acionado quando ocorre um click em qualquer área da tabela
    private void tableButtonClicked(java.awt.event.MouseEvent evt) {
        // Através da posição do click do mouse dentro da tabela,
        // determina qual a linha e qual a coluna em questão
        int colunaClicada = jTable1.getColumnModel().getColumnIndexAtX(evt.getX());
        int linhaClicada = evt.getY() / jTable1.getRowHeight();
        // Captura o 'id' na primeira posição da linha em questão
        int idEmQuestao = (int) jTable1.getModel().getValueAt(linhaClicada, 0);

        JOptionPane.showMessageDialog(null, "ID: " + idEmQuestao);

        // Dispara ações se as colunas com índice 3 (editar) ou 4 (remover)
        if (colunaClicada == 3) {
            // Abre uma nova janela de edição passando o 'id' em questão e a ponte de conexão
//            AlunoEditar telaEditar = new AlunoEditar(idEmQuestao, connection);
//            telaEditar.setVisible(true);
//            this.dispose();

        } else if (colunaClicada == 4) {
            // Executa a ação para remover o registro
            //TODO
        }

    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        private String text;

        public ButtonRenderer(String text) {
            this.text = text;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(this.text);
            return this;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
