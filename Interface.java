import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.awt.event.ActionEvent;

import alice.tuprolog.*;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import alice.tuprolog.*;

public class Interface extends JFrame {
	private ArrayList<String> estadoAtual = new ArrayList<String>(Arrays.asList("1,2,3,4,5,6,7,8,9".split(",")));
	private ArrayList<String> novoEstado = new ArrayList<>();
	private JPanel contentPane;
	private String status = "Em andamento";
	private String posicao = "";
	private String jogador = "x";
	private Prolog engine;
	private Theory theory;
	private SolveInfo info;
	private boolean isPC = false;

	private JButton bt1 = new JButton("");
	private JButton bt2 = new JButton("");
	private JButton bt3 = new JButton("");
	private JButton bt4 = new JButton("");
	private JButton bt5 = new JButton("");
	private JButton bt6 = new JButton("");
	private JButton bt7 = new JButton("");
	private JButton bt8 = new JButton("");
	private JButton bt9 = new JButton("");
	private Font f = new Font("SansSerif", Font.BOLD, 100);
	JLabel lblStatus = new JLabel("Em andamento");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidTheoryException 
	 */
	public Interface() throws FileNotFoundException, IOException, InvalidTheoryException {
		engine = new Prolog();
		theory =  new Theory(new FileInputStream("instrucoes.pl"));
		engine.setTheory(theory);
		
		setTitle("Jogo da Velha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt1.setFont(f);
					bt1.setText(jogador);		
					proximaJogada(1); //BOTAO 1
			}
		});
		bt1.setBounds(10, 11, 145, 125);
		contentPane.add(bt1);
		
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt4.setFont(f);
					bt4.setText(jogador);
					proximaJogada(4); //BOTAO 4
			}
		});
		bt4.setBounds(10, 136, 145, 125);
		contentPane.add(bt4);
		
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt2.setFont(f);
					bt2.setText(jogador);
					proximaJogada(2); //BOTAO 2
			}
		});
		bt2.setBounds(175, 11, 145, 125);
		contentPane.add(bt2);
		
		bt8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt8.setFont(f);
					bt8.setText(jogador);
					proximaJogada(8); //BOTAO 8
			}
		});
		bt8.setBounds(175, 261, 145, 125);
		contentPane.add(bt8);

		bt7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt7.setFont(f);
					bt7.setText(jogador);
					proximaJogada(7); //BOTAO 7
			}
		});
		bt7.setBounds(10, 261, 145, 125);
		contentPane.add(bt7);
		
		bt9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt9.setFont(f);
					bt9.setText(jogador);
					proximaJogada(9); //BOTAO 9
			}
		});
		bt9.setBounds(337, 261, 145, 125);
		contentPane.add(bt9);
		
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt3.setFont(f);
					bt3.setText(jogador);
					proximaJogada(3); //BOTAO 3
			}
		});
		bt3.setBounds(337, 11, 145, 125);
		contentPane.add(bt3);
		
		bt6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt6.setFont(f);
					bt6.setText(jogador);	
					proximaJogada(6); //BOTAO 6
			}
		});
		bt6.setBounds(337, 136, 145, 125);
		contentPane.add(bt6);
		
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					bt5.setFont(f);
					bt5.setText(jogador);	
					proximaJogada(5); //BOTAO 5
			}
		});
		bt5.setBounds(175, 136, 145, 125);
		contentPane.add(bt5);
		
		JLabel lblUserVsComputer = new JLabel("User    vs   Computer");
		lblUserVsComputer.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblUserVsComputer.setBounds(564, 11, 192, 24);
		contentPane.add(lblUserVsComputer);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(574, 46, 19, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblO = new JLabel("O");
		lblO.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblO.setForeground(Color.RED);
		lblO.setBounds(696, 46, 19, 24);
		contentPane.add(lblO);

		lblStatus.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblStatus.setBounds(596, 200, 150, 30);
		contentPane.add(lblStatus);				
		
	}
	
	public void prolog(int pos) throws Exception { 
		try {
			info = engine.solve("start("+estadoAtual.toString().trim()+","+pos+",NovoHashtagNovo).");
			if(info.isSuccess()){
				String str = info.getTerm("NovoHashtagNovo").toString();
				str = str.substring(1,18);
				novoEstado = new ArrayList<String>(Arrays.asList(str.split(",")));
			}
			else {
				System.out.println("Algo deu errado!");
			}
			
			System.out.println(info.getSolution());
		} catch (MalformedGoalException e) {
			System.out.println("Erro: " + e + " - Detalhes: " + e.getMessage());
		} catch (NoSolutionException e) {			
			System.out.println("Error" + e);
		}
	}
	
	public void setEstadoAtual(ArrayList<String> estadoAtual) {
		this.estadoAtual = estadoAtual;
	}
	
	public ArrayList<String> getEstadoAtual() {
		return this.estadoAtual;
	}

	public void setNovoEstado(ArrayList<String> novoEstado) {
		this.novoEstado = novoEstado;
	}
	
	public ArrayList<String> getNovoEstado() {
		return this.novoEstado;
	}	
	
	private void proximaJogada(int pos) {
		updateBt(pos, jogador);
		estadoAtual.set(pos-1, jogador);
		isPC = true;
		try {
			prolog(pos);
			consolidarJogada();
		}catch(Exception e){
			System.out.println("Erro: " +e.getMessage());
		}
		vitoria(estadoAtual);
		vitoria(novoEstado);
	}
	
	public void consolidarJogada(){
		
		Iterator it = novoEstado.iterator();
		int pos = 0;
		while(it.hasNext()){
			String jg = it.next().toString();
			if (isPC && !jg.equals(estadoAtual.get(pos))){
				estadoAtual.set(pos, jg); 
				updateBt(pos+1, jg);
				isPC = false;
			}
			pos++;
		}
		System.out.println(">>>"+novoEstado.toString());
		System.out.println(">>>"+estadoAtual.toString());
		vitoria(estadoAtual);
		vitoria(novoEstado);
	}

	public void updateBt(int pos,String jg){
		
		switch(pos){
			case 1: bt1.setText(jg);bt1.setFont(f);bt1.setEnabled(false);break;
			case 2: bt2.setText(jg);bt2.setFont(f);bt2.setEnabled(false);break;
			case 3: bt3.setText(jg);bt3.setFont(f);bt3.setEnabled(false);break;
			case 4: bt4.setText(jg);bt4.setFont(f);bt4.setEnabled(false);break;
			case 5: bt5.setText(jg);bt5.setFont(f);bt5.setEnabled(false);break;
			case 6: bt6.setText(jg);bt6.setFont(f);bt6.setEnabled(false);break;
			case 7: bt7.setText(jg);bt7.setFont(f);bt7.setEnabled(false);break;
			case 8: bt8.setText(jg);bt8.setFont(f);bt8.setEnabled(false);break;
			case 9: bt9.setText(jg);bt9.setFont(f);bt9.setEnabled(false);break;	
		}		
	}

	
	public void desabilitarBt() {
		
		bt1.setEnabled(false);
		bt2.setEnabled(false);
		bt3.setEnabled(false);
		bt4.setEnabled(false);
		bt5.setEnabled(false);
		bt6.setEnabled(false);
		bt7.setEnabled(false);
		bt8.setEnabled(false);
		bt9.setEnabled(false);
		
	}
	
	public void finalizar(int cod){
		
		desabilitarBt();
		switch(cod){
			case 1: bt1.setBackground(Color.RED);bt2.setBackground(Color.RED);bt3.setBackground(Color.RED);break;
			case 2: bt4.setBackground(Color.RED);bt5.setBackground(Color.RED);bt6.setBackground(Color.RED);break;
			case 3: bt7.setBackground(Color.RED);bt8.setBackground(Color.RED);bt9.setBackground(Color.RED);break;
			case 4: bt1.setBackground(Color.RED);bt4.setBackground(Color.RED);bt7.setBackground(Color.RED);break;
			case 5: bt2.setBackground(Color.RED);bt5.setBackground(Color.RED);bt8.setBackground(Color.RED);break;
			case 6: bt3.setBackground(Color.RED);bt6.setBackground(Color.RED);bt9.setBackground(Color.RED);break;
			case 7: bt1.setBackground(Color.RED);bt5.setBackground(Color.RED);bt9.setBackground(Color.RED);break;
			case 8: bt3.setBackground(Color.RED);bt5.setBackground(Color.RED);bt7.setBackground(Color.RED);break;
		}		
	}	
	
	public void vitoria(ArrayList<String> l) {
		if(l.get(0).equals(l.get(1)) && l.get(1).equals(l.get(2))) {lblStatus.setText(l.get(0).toUpperCase()+" Venceu"); finalizar(1);}
		if(l.get(3).equals(l.get(4)) && l.get(4).equals(l.get(5))) {lblStatus.setText(l.get(3).toUpperCase()+" Venceu"); finalizar(2);}
		if(l.get(6).equals(l.get(7)) && l.get(7).equals(l.get(8))) {lblStatus.setText(l.get(6).toUpperCase()+" Venceu"); finalizar(3);}
		
		if(l.get(0).equals(l.get(3)) && l.get(3).equals(l.get(6))) {lblStatus.setText(l.get(0).toUpperCase()+" Venceu"); finalizar(4);}
		if(l.get(1).equals(l.get(4)) && l.get(4).equals(l.get(7))) {lblStatus.setText(l.get(1).toUpperCase()+" Venceu"); finalizar(5);}
		if(l.get(2).equals(l.get(5)) && l.get(5).equals(l.get(8))) {lblStatus.setText(l.get(2).toUpperCase()+" Venceu"); finalizar(6);}
		
		if(l.get(0).equals(l.get(4)) && l.get(4).equals(l.get(8))) {lblStatus.setText(l.get(0).toUpperCase()+" Venceu"); finalizar(7);}
		if(l.get(2).equals(l.get(4)) && l.get(4).equals(l.get(6))) {lblStatus.setText(l.get(2).toUpperCase()+" Venceu"); finalizar(8);}
	}
	
}
