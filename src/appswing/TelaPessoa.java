/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Pessoa;
import regras_negocio.Fachada;

public class TelaPessoa {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_1;
	private JTextField textField;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JTextField textField_3;
	private JLabel label_8;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel label_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPessoa tela = new TelaPessoa();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPessoa() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		
		frame.setResizable(false);
		frame.setTitle("Cliente");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_4.setText("selecionado="+ (String) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.ORANGE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		button_1 = new JButton("Criar nova pessoa");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
							|| textField_3.getText().isEmpty() || textField_4.getText().isEmpty() || textField_5.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nome = textField_1.getText();
					String grau = textField.getText();
					int grauAmizade = Integer.parseInt(grau);
					String data = textField_2.getText();
					String rua = textField_3.getText();
					String num = textField_4.getText();
					int numero = Integer.parseInt(num);
					String bairro = textField_5.getText();
					Fachada.cadastrarPessoa(nome, rua, numero, bairro, grauAmizade, data);
					label.setText("cliente criado: "+ nome);
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(520, 263, 153, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(308, 11, 89, 23);
		frame.getContentPane().add(button);

		label_3 = new JLabel("Nome:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 229, 63, 14);
		frame.getContentPane().add(label_3);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(58, 226, 168, 20);
		frame.getContentPane().add(textField_1);

		button_2 = new JButton("Apagar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						label.setText("nao implementado " );
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
						Fachada.excluirPessoa(nome);
						label.setText("pessoa apagada" );
						listagem();
					}
					else
						label.setText("nao selecionado");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(515, 200, 171, 23);
		frame.getContentPane().add(button_2);
		
		label_1 = new JLabel("Grau de amizade:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(10, 253, 112, 14);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(111, 256, 80, 20);
		frame.getContentPane().add(textField);
		
		label_2 = new JLabel("Data de nascimento:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(10, 292, 131, 14);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(131, 289, 131, 20);
		frame.getContentPane().add(textField_2);
		
		label_5 = new JLabel("numero inteiro");
		label_5.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label_5.setForeground(Color.GRAY);
		label_5.setBounds(21, 269, 80, 13);
		frame.getContentPane().add(label_5);
		
		label_6 = new JLabel("Rua:");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(275, 232, 63, 14);
		frame.getContentPane().add(label_6);
		
		label_7 = new JLabel("Endereco");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(322, 205, 75, 13);
		frame.getContentPane().add(label_7);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(322, 229, 168, 20);
		frame.getContentPane().add(textField_3);
		
		label_8 = new JLabel("Numero:");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_8.setBounds(275, 267, 63, 14);
		frame.getContentPane().add(label_8);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(332, 264, 99, 20);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(322, 291, 168, 20);
		frame.getContentPane().add(textField_5);
		
		label_9 = new JLabel("Bairro:");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_9.setBounds(275, 289, 63, 14);
		frame.getContentPane().add(label_9);
	}

	public void listagem() {
		try{
			List<Pessoa> lista = Fachada.listarPessoas();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("nome");
			model.addColumn("endereco");
			model.addColumn("grauAmizade");
			model.addColumn("dtNascimento");

			//adicionar linhas no model
			for(Pessoa pess : lista) {
				model.addRow(new Object[]{pess.getNome(), pess.getEndereco(), pess.getGrauAmizade(), pess.getDtNascimento()} );
			}

			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
