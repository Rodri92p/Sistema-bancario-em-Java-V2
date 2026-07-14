package Controllers;
				
import javafx.animation.TranslateTransition;
				import javafx.scene.Node;
				import javafx.util.Duration;
				import java.time.LocalDate;
				import java.time.LocalTime;
				import java.time.format.DateTimeFormatter;
				import java.util.Locale;
				import java.util.UUID;
				
				import Controllers.CambioAPI.Cambio;
				import java.io.IOException;
				import java.math.RoundingMode;
				import java.text.NumberFormat;
				import System.Client;
				import System.DAO;
				import System.Investment;
				import System.TipoInvestimento;
				import javafx.animation.Animation;
				import javafx.animation.FadeTransition;
				import javafx.animation.PauseTransition;
				import javafx.fxml.FXML;
				import javafx.fxml.FXMLLoader;
				import javafx.scene.Parent;
				import javafx.scene.Scene;
				import javafx.scene.control.Button;
				import javafx.scene.control.Label;
				import javafx.scene.control.PasswordField;
				import javafx.scene.control.RadioButton;
				import javafx.scene.control.TextField;
				import javafx.scene.image.ImageView;
				import javafx.scene.layout.AnchorPane;
				import javafx.scene.shape.Circle;
				import javafx.scene.shape.Line;
				import javafx.scene.text.Text;
				import javafx.stage.Modality;
				import javafx.stage.Stage;
				
public class Controller {
				
private static Cambio cambioAtual;
private Client cliente;
private Investment investimentoBR;
private Investment investimentoUS;
private Client cliente_pix;
String Caminho;
String cpftexto;
String nometexto;
String chavetexto;
int tipo;
int PIX_OP = 0;

//--------------------------------------------------------------------------------------------------
//VARIAVEIS
				
//TEXT
@FXML private Text txt_cliente;               @FXML private Text txt_saldo;          		@FXML private Text TXT_pesoARS; 
@FXML private Text TXT_dolarUSD;           	  @FXML private Text TXT_euro;           		@FXML private Text TXT_libra;
@FXML private Text TXT_franco;             	  @FXML private Text TXT_ienne;          		@FXML private Text TXT_yuan;
@FXML private Text TXT_dolarAUS;              @FXML private Text TXT_dolarCND;       		@FXML private Text TXT_pesoMXN;
@FXML private Text TXT_coroa;                 @FXML private Text fav_1;              		@FXML private Text fav_2;
@FXML private Text fav_3;                     @FXML private Text fav_4;              		@FXML private Text fav_5;
@FXML private Text fav_6;                     @FXML private Text fav_7;                     @FXML private Text erro_pixvazio;
@FXML private Text erro_opcaovazia;           @FXML private Text erro_opcaovazia2;  	    @FXML private Text erro_letranopix;
@FXML private Text erro_pixinexistente;       @FXML private Text erro_pixparasi;            @FXML private Text erro_emailinvalido;
@FXML private Text erro_saldoinsuficiente;    @FXML private Text erro_valorinvalido;        @FXML private Text lbl_cpfpix;
@FXML private Text lbl_nomepix;            	  @FXML private Text lbl_datapix;               @FXML private Text nenhum_TXT;
@FXML private Text txt_datainvestimentoUS;    @FXML private Text txt_datainvestimentoBR;    @FXML private Text txt_valorlucroUS;
@FXML private Text txt_valorlucroBR;          @FXML private Text txt_valorcofreUS;          @FXML private Text txt_valorcofreBR;
@FXML private Text txt_ultimatransacao;       @FXML private Text txt_dataultimatransacao;   @FXML private Text txt_penultimatransacao;
@FXML private Text txt_datapenultimatransacao;@FXML private Text txt_antepenultimatransacao;@FXML private Text txt_dataantepenultimatransacao;
@FXML private Text erroalterar_1;             @FXML private Text erroalterar_2;             @FXML private Text erroalterar_3;
@FXML private Text erroalterar_4;             @FXML private Text erroalterar_5;             @FXML private Text erroalterar_6;
@FXML private Text txt_data_nova;             @FXML private Text txt_senhanova1;            @FXML private Text txt_senhanova2;
@FXML private Text txt_senhanova3;            @FXML private Text erro_alterarpix1;          @FXML private Text erro_alterarpix2;
@FXML private Text erro_alterarpix3;          @FXML private Text erro_alterarpix4;          @FXML private Text erro_alterarpix5;
@FXML private Text TXT_chavecpf;
@FXML private Text TXT_chavecelular;          @FXML private Text TXT_chaveemail;            @FXML private Text TXT_chavealeatoria;


//BUTTON
@FXML private Button btn_sair;                @FXML private Button btn_investirBR;          @FXML private Button btn_resgatarBR;
@FXML private Button btn_sairsim;             @FXML private Button btn_sairnao;             @FXML private Button btn_sairok;
@FXML private Button btn_pix;                 @FXML private Button btn_inicio;              @FXML private Button btn_extrato;
@FXML private Button btn_conta;               @FXML private Button btn_cofrinho;            @FXML private Button btn_ferramentas;
@FXML private Button btn_altchave;            @FXML private Button btn_altsenha;            @FXML private Button btn_excluirconta;
@FXML private Button btn_investirUS;          @FXML private Button btn_resgatarUS;          @FXML private Button btn_login;
@FXML private Button btn_registro;            @FXML private Button btn_favadd1;             @FXML private Button btn_favadd2;
@FXML private Button btn_favadd4;	          @FXML private Button btn_favadd5;     		@FXML private Button btn_favadd6;
@FXML private Button btn_favadd7;             @FXML private Button btn_favdelete1;   		@FXML private Button btn_favdelete2;
@FXML private Button btn_favdelete3;          @FXML private Button btn_favdelete4;   		@FXML private Button btn_favdelete5;
@FXML private Button btn_favdelete6;          @FXML private Button btn_favdelete7;   		@FXML private Button btn_confirmardestino;
@FXML private Button btn_favoritarpix;        @FXML private Button btn_confirmarpix; 		@FXML private Button btn_areaextrato; 
@FXML private Button btn_areacofrinho;        @FXML private Button btn_trabalho;     		@FXML private Button btn_sobrenos;
@FXML private Button btn_atendimento;         @FXML private Button btn_favadd3;             @FXML private Button btn_confirmar_excluirconta;
@FXML private Button btn_adcpix_cpf;          @FXML private Button btn_adcpix_celular;      @FXML private Button btn_adcpix_email;
@FXML private Button btn_adcpix_aleatoria;    @FXML private Button btn_apagarpix_cpf;       @FXML private Button btn_apagarpix_celular;   
@FXML private Button btn_apagarpix_email;     @FXML private Button btn_apagarpix_aleatoria; @FXML private Button btn_SIM;

//TEXTFIELD + PASSWORDFIELD	3
@FXML private TextField cpf_login;            @FXML private TextField cpf_registro;         @FXML private TextField email_registro;
@FXML private TextField nome_registro;        @FXML private TextField sobrenome_registro;   @FXML private TextField field_valorpix; 
@FXML private TextField field_senhapix;       @FXML private TextField field_inserirpix;     @FXML private TextField field_confirmesenhapix;
@FXML private TextField field_confirmarnova;  @FXML private TextField field_senhaantiga;    @FXML private TextField field_senhanova;
@FXML private TextField field_chavepix;       @FXML private TextField field_confirmechave;    
@FXML private PasswordField senha_login;      @FXML private PasswordField senha_registro;   @FXML private PasswordField confirme_registro;

//LABEL
@FXML private Label senha_loginTXT;           @FXML private Label cpf_loginTXT;             @FXML private Label nome_registroTXT;
@FXML private Label sobrenome_registroTXT;    @FXML private Label senha_registroTXT;        @FXML private Label cpf_registroTXT;
@FXML private Label email_registroTXT;        @FXML private Label confirme_registroTXT;     @FXML private Label erro_cpf1;
@FXML private Label erro_cpf2;                @FXML private Label erro_cpf3;                @FXML private Label erro_cpf4;
@FXML private Label erro_cpflogin1;           @FXML private Label erro_cpflogin2;           @FXML private Label erro_senha;
@FXML private Label erro_senhalogin1;         @FXML private Label erro_senhalogin2;         @FXML private Label erro_nome;
@FXML private Label erro_sobrenome;           @FXML private Label erro_confirma;            @FXML private Label erro_email;
@FXML private Label confirme_pixTXT;          @FXML private Label senha_pixTXT;             @FXML private Label erro_senhapixvazia;
@FXML private Label erro_confirmarsenhavazio; @FXML private Label erro_senhapix1;           @FXML private Label erro_senhapix2;
@FXML private Label chave_pixTXT;             @FXML private Label confirmar_pixTXT;

//LINE
@FXML private Line barra_1;                   @FXML private Line fav_barra7;
@FXML private Line barra_2;                   @FXML private Line barra_3; 					@FXML private Line barra_4;
@FXML private Line barra_5;                   @FXML private Line barra_6; 					@FXML private Line barra_7;
@FXML private Line barra_8;                   @FXML private Line barra_9; 					@FXML private Line barra_10;
@FXML private Line barra_senha1;              @FXML private Line barra_senha2;              @FXML private Line barra_senha3;
@FXML private Line fav_barra1;                @FXML private Line fav_barra2; 				@FXML private Line fav_barra3;
@FXML private Line fav_barra4;                @FXML private Line fav_barra5; 				@FXML private Line fav_barra6;

//SHAPES, ANCHORS, RADIO, IMAGES, ETC...
@FXML private AnchorPane loadingPane; 
@FXML private Circle dot1, dot2, dot3;
@FXML private ImageView sad_facepix;
@FXML private ImageView emoji_1;                        @FXML private ImageView emoji_2;                        @FXML private ImageView emoji_3;
@FXML private RadioButton radio_aleatoriapix;           @FXML private RadioButton radio_emailpix;               @FXML private RadioButton radio_concordarexcluir;
@FXML private RadioButton radio_telefonepix;            @FXML private RadioButton radio_cpfpix;                 @FXML private RadioButton btn_ciente_senhanova;
@FXML private javafx.scene.shape.Rectangle quadrado_1;  @FXML private javafx.scene.shape.Rectangle quadrado_2;  @FXML private javafx.scene.shape.Rectangle quadrado_3;
@FXML private javafx.scene.shape.Rectangle retangulo_1; @FXML private javafx.scene.shape.Rectangle retangulo_2; @FXML private javafx.scene.shape.Rectangle retangulo_3;

//--------------------------------------------------------------------------------------------------
		
//UTILIDADES, TRANSIÇÕES E PASSAGEM DE DADOS
public void PassarDados(Client cliente) {
		this.cliente = cliente;
		atualizarFavoritos();
		cambioTela();
		investimentoTela();
		atualizarchaves();
		txt_cliente.setText(cliente.getNome().toUpperCase()+" "+cliente.getSobrenome().toUpperCase());
	    txt_saldo.setText("R$ " + cliente.getSaldo());
	}
	
public void receberPix(Client clientePix) {
		
		this.cliente_pix = clientePix;
		
	    String cpfcliente = clientePix.getCpf();
	    
	    LocalDate hoje = LocalDate.now();
	
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	    String dataFormatada = hoje.format(formato);
	
	    lbl_datapix.setText(dataFormatada);
	    lbl_nomepix.setText(
	        clientePix.getNome() + " " +
	        clientePix.getSobrenome()
	    );
	
	    lbl_cpfpix.setText(
	        "**-***." + cpfcliente.substring(3, 6) + ".***"
	    );
	}
	
private void atualizarFavoritos() {
	    if(btn_favadd1 == null) {
	        return;
	    }
	
	    Integer[] favoritos = {
	        cliente.getFavorito1(),
	        cliente.getFavorito2(),
	        cliente.getFavorito3(),
	        cliente.getFavorito4(),
	        cliente.getFavorito5(),
	        cliente.getFavorito6(),
	        cliente.getFavorito7()
	    };
	    
	    Integer[] tipos = {
	        cliente.getFavorito1tipo(),
	        cliente.getFavorito2tipo(),
	        cliente.getFavorito3tipo(),
	        cliente.getFavorito4tipo(),
	        cliente.getFavorito5tipo(),
	        cliente.getFavorito6tipo(),
	        cliente.getFavorito7tipo()
	    };
	
	    Button[] btnAdd = {
	        btn_favadd1,
	        btn_favadd2,
	        btn_favadd3,
	        btn_favadd4,
	        btn_favadd5,
	        btn_favadd6,
	        btn_favadd7
	    };
	    
	    Text[] txtFav = {
	        fav_1,
	        fav_2,
	        fav_3,
	        fav_4,
	        fav_5,
	        fav_6,
	        fav_7,
	    };
	
	    Button[] btnDelete = {
	        btn_favdelete1,
	        btn_favdelete2,
	        btn_favdelete3,
	        btn_favdelete4,
	        btn_favdelete5,
	        btn_favdelete6,
	        btn_favdelete7
	    };
	
	    Line[] barras = {
	        fav_barra1,
	        fav_barra2,
	        fav_barra3,
	        fav_barra4,
	        fav_barra5,
	        fav_barra6,
	        fav_barra7
	    };
		
	    DAO<Client> dao = new DAO<>(Client.class);
	
	    for(int i = 0; i < 7; i++) {
	    	
	    	if (favoritos[i] == null) {
	
	    	    txtFav[i].setVisible(false);
	    	    btnAdd[i].setVisible(false);
	    	    btnDelete[i].setVisible(false);
	    	    barras[i].setVisible(false);
	
	    	    continue;
	    	}
	    	
	    	Client favorito = dao.BuscarPorId(favoritos[i]);
	    	
	        if(favoritos[i] != null) {
	
	        	if(favoritos[0] != null) {
	                sad_facepix.setVisible(false);
	                nenhum_TXT.setVisible(false);
	         	}
	
	            btnAdd[i].setVisible(true);
	            txtFav[i].setVisible(true);
	            btnDelete[i].setVisible(true);
	            barras[i].setVisible(true);
	            txtFav[i].setText(
	                favorito.getNome()             +
	                " "                            +
	                favorito.getSobrenome()        +
	                " - CHAVE: "                   +
	                favorito.getChavePixPrincipal(tipos[i])
	            );
	
	        } else {    	
	        	if(favoritos[0] == null) {
	                sad_facepix.setVisible(true);
	                nenhum_TXT.setVisible(true);
	         	}
	        	txtFav[i].setVisible(false);
	            btnAdd[i].setVisible(false);
	            btnDelete[i].setVisible(false);
	            barras[i].setVisible(false);
	        }
	    }
	}
	
private void atualizarchaves() {
	
	    if (TXT_chavecpf == null) return;
	
	    TXT_chavecpf.setText(
	        cliente.getPix_cpf() == null
	            ? "NENHUMA CHAVE INFORMADA"
	            : cliente.getChavePixPrincipal(1));
	
	    TXT_chavecelular.setText(
	        cliente.getPix_celular() == null
	            ? "NENHUMA CHAVE INFORMADA"
	            : cliente.getChavePixPrincipal(3));
	
	    TXT_chaveemail.setText(
	        cliente.getPix_email() == null
	            ? "NENHUMA CHAVE INFORMADA"
	            : cliente.getChavePixPrincipal(2));
	
	    TXT_chavealeatoria.setText(
	        cliente.getPix_aleatorio() == null
	            ? "NENHUMA CHAVE INFORMADA"
	            : cliente.getChavePixPrincipal(4));
	}
	
@SuppressWarnings("deprecation")
public void cambioTela() {
		if(cambioAtual == null) {
	
		    CambioAPI api = new CambioAPI();
		    cambioAtual = api.cambioAtual();
	
		}
		
		if (TXT_dolarUSD != null) {
	
		    CambioAPI api = new CambioAPI();
		    Cambio cambio = api.cambioAtual();
	
		    NumberFormat nf = NumberFormat.getCurrencyInstance(
		    new Locale("pt", "BR"));
		    
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		    String hora = LocalTime.now().format(formatter);
	
		    txt_data_nova.setText(hora);
	
		    TXT_dolarUSD.setText(nf.format(cambio.getUsd()));
		    TXT_dolarAUS.setText(nf.format(cambio.getAud()));
		    TXT_dolarCND.setText(nf.format(cambio.getCad()));
		    TXT_euro.setText(nf.format(cambio.getEur()));
		    TXT_yuan.setText(nf.format(cambio.getCny()));
		    TXT_coroa.setText(nf.format(cambio.getSek()));
		    TXT_pesoMXN.setText(nf.format(cambio.getMxn()));
		    TXT_libra.setText(nf.format(cambio.getGbp()));
		    TXT_pesoARS.setText(nf.format(cambio.getArs()));
		    TXT_franco.setText(nf.format(cambio.getChf()));
		    TXT_ienne.setText(nf.format(cambio.getJpy()));
		}
		
	}
	
@SuppressWarnings("deprecation")
public void investimentoTela() {
	
		if(txt_valorcofreBR != null) {
			
		  DAO<Client> dao = new DAO<>(Client.class);
		  investimentoBR = dao.buscarPorClienteETipo(cliente,TipoInvestimento.BRASIL);
		  investimentoUS = dao.buscarPorClienteETipo(cliente,TipoInvestimento.DOLAR);
		  
		  if(investimentoUS != null) {
			  NumberFormat nf = NumberFormat.getNumberInstance(new Locale("en", "US"));
			  nf.setMinimumFractionDigits(2);
			  nf.setMaximumFractionDigits(2);
	
			  txt_valorcofreUS.setText(nf.format(investimentoUS.calcularValorAtual()));  
			  txt_valorlucroUS.setText(investimentoUS.calcularValorizacao().setScale(2, RoundingMode.HALF_UP).toString() + "%"); 
			  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			  String datainvestimento = investimentoUS.getDataInvestimento().format(formatter);
			  txt_datainvestimentoUS.setText(datainvestimento);
			  
			  dao.atualizarRendimento(investimentoUS);
		  } 
		  else 
		  {
			  txt_valorcofreUS.setText("0,00");  
			  txt_valorlucroUS.setText("0%");  
			  txt_datainvestimentoUS.setText("N/A");  
		  }
		  
		  
		  if(investimentoBR != null) {
			  
			  NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
			  nf.setMinimumFractionDigits(2);
			  nf.setMaximumFractionDigits(2);
			  
			  txt_valorcofreBR.setText(nf.format(investimentoBR.calcularValorAtual()));  
			  txt_valorlucroBR.setText(investimentoBR.calcularValorizacao().setScale(2, RoundingMode.HALF_UP).toString() + "%");
			  
			  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			  String datainvestimento = investimentoBR.getDataInvestimento().format(formatter);
			  txt_datainvestimentoBR.setText(datainvestimento);  
			  
			  dao.atualizarRendimento(investimentoBR);
		  }
	
		  else
		  {
			  txt_valorcofreBR.setText("0,00");  
			  txt_valorlucroBR.setText("0%");  
			  txt_datainvestimentoBR.setText("N/A"); 
		  }
	
	
	}
	}
	
	@FXML
private void deletarFavoritos() {
	  
	  //DELETA FAVORITO 1
	  if(btn_favdelete1.isPressed()) {
		  boolean removido = cliente.removerFavorito(
		            cliente.getFavorito1(),
		            cliente.getFavorito1tipo()
		  );
		  
		    if(removido) {
	
		        DAO<Client> dao = new DAO<>(Client.class);
	
		        dao.abrir();
		        dao.atualizar(cliente);
		        dao.fechar();
	
		        atualizarFavoritos();
		    }
	  }
	  
	  //DELETA FAVORITO 2
	  if(btn_favdelete2.isPressed()) {
		  boolean removido = cliente.removerFavorito(
				  cliente.getFavorito2(),
				  cliente.getFavorito2tipo()
				  );
		  
		  if(removido) {
			  
			  DAO<Client> dao = new DAO<>(Client.class);
			  
			  dao.abrir();
			  dao.atualizar(cliente);
			  dao.fechar();
			  
			  atualizarFavoritos();
		  }
	  }
	  
	  //DELETA FAVORITO 3
	  if(btn_favdelete3.isPressed()) {
		  boolean removido = cliente.removerFavorito(
				  cliente.getFavorito3(),
				  cliente.getFavorito3tipo()
				  );
		  
		  if(removido) {
			  
			  DAO<Client> dao = new DAO<>(Client.class);
			  
			  dao.abrir();
			  dao.atualizar(cliente);
			  dao.fechar();
			  
			  atualizarFavoritos();
		  }
	  }
	  
	  //DELETA FAVORITO 4
	  if(btn_favdelete4.isPressed()) {
		  boolean removido = cliente.removerFavorito(
				  cliente.getFavorito4(),
				  cliente.getFavorito4tipo()
				  );
		  
		  if(removido) {
			  
			  DAO<Client> dao = new DAO<>(Client.class);
			  
			  dao.abrir();
			  dao.atualizar(cliente);
			  dao.fechar();
			  
			  atualizarFavoritos();
		  }
	  }
	  
	  //DELETA FAVORITO 5
	  if(btn_favdelete5.isPressed()) {
		  boolean removido = cliente.removerFavorito(
				  cliente.getFavorito5(),
				  cliente.getFavorito5tipo()
				  );
		  
		  if(removido) {
			  
			  DAO<Client> dao = new DAO<>(Client.class);
			  
			  dao.abrir();
			  dao.atualizar(cliente);
			  dao.fechar();
			  
			  atualizarFavoritos();
		  }
	  }
	  
	  //DELETA FAVORITO 6
	  if(btn_favdelete6.isPressed()) {
		  boolean removido = cliente.removerFavorito(
				  cliente.getFavorito6(),
				  cliente.getFavorito6tipo()
				  );
		  
		  if(removido) {
			  
			  DAO<Client> dao = new DAO<>(Client.class);
			  
			  dao.abrir();
			  dao.atualizar(cliente);
			  dao.fechar();
			  
			  atualizarFavoritos();
		  }
	  }
	  
	  //DELETA FAVORITO 7
	  if(btn_favdelete7.isPressed()) {
		  boolean removido = cliente.removerFavorito(
				  cliente.getFavorito7(),
				  cliente.getFavorito7tipo()
				  );
		  
		  if(removido) {
			  
			  DAO<Client> dao = new DAO<>(Client.class);
			  
			  dao.abrir();
			  dao.atualizar(cliente);
			  dao.fechar();
			  
			  atualizarFavoritos();
		  }
	  }
	}
	
public void Animar() {
	TranslateTransition t1 = new TranslateTransition(Duration.millis(300), dot1);
	   t1.setByY(-10);
	   t1.setAutoReverse(true);
	   t1.setCycleCount(Animation.INDEFINITE);
	
	TranslateTransition t2 = new TranslateTransition(Duration.millis(300), dot2);
	   t2.setByY(-10);
	   t2.setAutoReverse(true);
	   t2.setCycleCount(Animation.INDEFINITE);
	   t2.setDelay(Duration.millis(100));
	
	TranslateTransition t3 = new TranslateTransition(Duration.millis(300), dot3);
	   t3.setByY(-10);
	   t3.setAutoReverse(true);
	   t3.setCycleCount(Animation.INDEFINITE);
	   t3.setDelay(Duration.millis(200));
	
	   t1.play();
	   t2.play();
	   t3.play();
	}
	
@SuppressWarnings("unused")
public void TransicaoTela(String Caminho, Button botao) {
		
	   loadingPane.setVisible(true);
	   Animar();
	
	   FadeTransition fadeIn = new FadeTransition(Duration.millis(200), loadingPane);
	   fadeIn.setFromValue(0);
	   fadeIn.setToValue(1);
	
	   fadeIn.setOnFinished(e1 -> {
	
		   PauseTransition delay = new PauseTransition(Duration.seconds(1));
			     
		   delay.setOnFinished(e2 -> {
			   FadeTransition fadeOut = new FadeTransition(Duration.millis(200), loadingPane);
			   fadeOut.setFromValue(1);
			   fadeOut.setToValue(0);
	
			   fadeOut.setOnFinished(e3 -> {
				   try {
					   FXMLLoader loader = new FXMLLoader(getClass().getResource(Caminho));
					   Parent root = loader.load();
	
					   Controller controller = loader.getController();
					   controller.PassarDados(cliente); 
	
					   Stage stage = (Stage) botao.getScene().getWindow();
					   stage.setScene(new Scene(root));
				   } catch (Exception ex) {
					   ex.printStackTrace();
				   }
			   });
	
			   fadeOut.play();
		   });
		   
		   delay.play();
	   });
	
	   fadeIn.play();
	}
	
@SuppressWarnings("unused")
public void TransicaoPix() throws IOException {
		
		   loadingPane.setVisible(true);
		   Animar();
	
		   FadeTransition fadeIn = new FadeTransition(Duration.millis(200), loadingPane);
		   fadeIn.setFromValue(0);
		   fadeIn.setToValue(1);
	
		   fadeIn.setOnFinished(e1 -> {
	
			   PauseTransition delay = new PauseTransition(Duration.seconds(1));
				     
			   delay.setOnFinished(e2 -> {
				   FadeTransition fadeOut = new FadeTransition(Duration.millis(200), loadingPane);
				   fadeOut.setFromValue(1);
				   fadeOut.setToValue(0);
	
				   fadeOut.setOnFinished(e3 -> {
					   try {
						   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Transferencia/Scr_Transferencia_2.fxml"));
						   Parent root = loader.load();
	
						   Controller controller = loader.getController();
						   controller.PassarDados(cliente); 
						   
	
						   Stage stage = (Stage) btn_confirmardestino.getScene().getWindow();
						   stage.setScene(new Scene(root));
						   controller.receberPix(cliente_pix);
					   } catch (Exception ex) {
						   ex.printStackTrace();
					   }
				   });
	
				   fadeOut.play();
			   });
			   
			   delay.play();
		   });
	
		   fadeIn.play();
	}
	
public void tremer(Node node) {
	
	    TranslateTransition tt = new TranslateTransition(Duration.millis(60), node);
	
	    tt.setFromX(0);
	    tt.setByX(10);
	
	    tt.setCycleCount(6);
	    tt.setAutoReverse(true);
	
	    tt.play();
	}
	
	@SuppressWarnings("unused")
public void Transicao() {
	
	//TRANSIÇÕES LATERAIS
	  if(btn_inicio != null && btn_inicio.isPressed()) {
		btn_inicio.setOnAction(e -> TransicaoTela("/application/Inicio/Scr_Inicial.fxml", btn_inicio));
	  return;  
	  }
	
	  if(btn_pix != null && btn_pix.isPressed()) {
	
		btn_pix.setOnAction(e -> TransicaoTela("/application/Transferencia/Scr_Transferencia_1.fxml", btn_pix));
	  return;  
	  }
	
	  if((btn_cofrinho != null && btn_cofrinho.isPressed())) {
		btn_cofrinho.setOnAction(e -> TransicaoTela("/application/Cofrinho/Scr_Cofrinho.fxml", btn_cofrinho));
	  return;  
	  }
	
	  if((btn_extrato != null && btn_extrato.isPressed())) {
		btn_extrato.setOnAction(e -> TransicaoTela("/application/Extrato/Scr_Extrato.fxml", btn_extrato));
	  return;  
	  }
	  
	  if((btn_areacofrinho != null && btn_areacofrinho.isPressed())) {
		 btn_areacofrinho.setOnAction(e -> TransicaoTela("/application/Cofrinho/Scr_Cofrinho.fxml", btn_areacofrinho));
	  return;  
	  }
	  
	  if((btn_areaextrato != null && btn_areaextrato.isPressed())) {
		 btn_areaextrato.setOnAction(e -> TransicaoTela("/application/Extrato/Scr_Extrato.fxml", btn_areaextrato));
	  return;  
	  }
	
	  if(btn_conta != null && btn_conta.isPressed()) {
		btn_conta.setOnAction(e -> TransicaoTela("/application/Conta/Scr_Conta.fxml", btn_conta));
	  return;  
	  }
	
	  if(btn_ferramentas != null && btn_ferramentas.isPressed()) {
		btn_ferramentas.setOnAction(e -> TransicaoTela("/application/Ferramentas/Scr_Ferramentas.fxml", btn_ferramentas));
	  return;  
	  }
	  
	//TRANSIÇÕES INTERNAS - CONTA
	  if(btn_altchave != null && btn_altchave.isPressed()) {
		btn_altchave.setOnAction(e -> TransicaoTela("/application/Conta/Scr_Alterarpix.fxml", btn_altchave));
	  return;  
	  }
	
	  if(btn_altsenha != null && btn_altsenha.isPressed()) {
		  btn_altsenha.setOnAction(e -> TransicaoTela("/application/Conta/Scr_Alterarsenha.fxml", btn_altsenha));
	  return;  
	  }
	
	  if(btn_excluirconta != null && btn_excluirconta.isPressed()) {
		  btn_excluirconta.setOnAction(e -> TransicaoTela("/application/Conta/Scr_Excluirconta.fxml", btn_excluirconta));
	  return;  
	  }
	  
	//TRANSIÇÕES INTERNAS - COFRINHO
	  if(btn_investirBR != null && btn_investirBR.isPressed()) {
		btn_investirBR.setOnAction(e -> TransicaoTela("/application/Cofrinho/Scr_InvestirBR.fxml", btn_investirBR));
	  return;  
	  }
	
	  if(btn_resgatarBR != null && btn_resgatarBR.isPressed()) {
		btn_resgatarBR.setOnAction(e -> TransicaoTela("/application/Cofrinho/Scr_ResgatarBR.fxml", btn_resgatarBR));
	  return;  
	  }
	
	  if(btn_investirUS != null && btn_investirUS.isPressed()) {
	    btn_investirUS.setOnAction(e -> TransicaoTela("/application/Cofrinho/Scr_InvestirUS.fxml", btn_investirUS));
	  return;  
	  }
	  
	  if(btn_resgatarUS != null && btn_resgatarUS.isPressed()) {
	    btn_resgatarUS.setOnAction(e -> TransicaoTela("/application/Cofrinho/Scr_ResgatarUS.fxml", btn_resgatarUS));
	  return;  
	  }
}
	
//LOGAR E ENTRAR NA TELA INICIAL
@FXML
private void FazerLogin() {
	
	// SE NÃO TIVER NADA
	if(cpf_login.getText().isEmpty() || senha_login.getText().isEmpty()) {
		   tremer(cpf_login);
		   tremer(cpf_loginTXT);
		   tremer(barra_7);
		   tremer(senha_login);
		   tremer(senha_loginTXT);
		   tremer(barra_8);
		   erro_cpflogin1.setVisible(true); erro_senhalogin1.setVisible(true); erro_senhalogin2.setVisible(false); erro_cpflogin2.setVisible(false); return; }
	else { erro_cpflogin1.setVisible(false); erro_senhalogin1.setVisible(false);  }
	
	DAO<Client> dao = new DAO<>(Client.class);
	cliente = dao.BuscarCliente(
		    cpf_login.getText()
	);
	
	// SE NÃO HOUVER CLIENTE
	if (cliente == null) {
		   tremer(cpf_login);
		   tremer(cpf_loginTXT);
		   tremer(barra_7);
		   erro_cpflogin2.setVisible(true); return;}
	else { erro_cpflogin2.setVisible(false); }
	
	//SE SENHA ESTIVER ERRADA (VISTO QUE SE O CPF TIVER ERRADO, O SISTEMA NEM VAI ACHAR O CLIENTE)
	if (!cliente.getSenha().equals(senha_login.getText())) {
		   tremer(senha_login);
		   tremer(senha_loginTXT);
		   tremer(barra_8);
		   erro_senhalogin2.setVisible(true); return; }
	else { erro_senhalogin2.setVisible(false); }
	
	//SE HOUVER CLIENTE + SENHA CORRETA = SISTEMA LIBERADO
	TransicaoTela("/application/Inicio/Scr_Inicial.fxml",btn_login);
	return;
}
	
@FXML
private void FazerRegistro() {
		
	// SE NÃO TIVER NADA OU ALGUM DADO FALTANDO
	if(cpf_registro.getText().isEmpty() || senha_registro.getText().isEmpty() || nome_registro.getText().isEmpty() ||
	   sobrenome_registro.getText().isEmpty() || confirme_registro.getText().isEmpty()){
		   tremer(cpf_registro); tremer(senha_registro); tremer(nome_registro); tremer(sobrenome_registro); tremer(confirme_registro);
		   tremer(cpf_registroTXT); tremer(senha_registroTXT); tremer(nome_registroTXT); tremer(sobrenome_registroTXT); tremer(email_registroTXT); tremer(confirme_registroTXT);
		   tremer(barra_1); tremer(barra_2); tremer(barra_3); tremer(barra_4); tremer(barra_5); tremer(barra_6);
		   erro_cpf3.setVisible(true); erro_cpf4.setVisible(false); return; }
	else { erro_cpf3.setVisible(false); }
	
	DAO<Client> dao = new DAO<>(Client.class);
	cliente = dao.BuscarCliente(
		    cpf_registro.getText()
	);	
	
	//CASO CLIENTE JÁ EXISTA
	if (cliente != null) {
		   tremer(cpf_registro);
		   tremer(cpf_registroTXT);
		   tremer(barra_1);
		   erro_cpf4.setVisible(true); return;}
	else { erro_cpf4.setVisible(false); }
	
	//CASO CLIENTE NÃO EXISTA E CPF COM MENOS DE 11 DIGITOS
	if (cliente == null) {
		if (cpf_registro.getText().length() != 11) {
			   tremer(cpf_registro);
			   tremer(cpf_registroTXT);
			   tremer(barra_1);
			   erro_cpf1.setVisible(true); return;}
	    else { erro_cpf1.setVisible(false); }
		
		
		// LETRA NO CPF
		if (!cpf_registro.getText().matches("[0-9]+")) {
			   erro_cpf1.setVisible(false);
			   tremer(cpf_registro);
			   tremer(cpf_registroTXT);
			   tremer(barra_1);
			   erro_cpf2.setVisible(true); return;}
		else { erro_cpf2.setVisible(false); }
		
		// NUMERO NO NOME
		if (!nome_registro.getText().matches("[a-zA-ZÀ-ÿ ]+")) {
			   tremer(nome_registro);
			   tremer(nome_registroTXT);
			   tremer(barra_2);
			   erro_nome.setVisible(true); return;}
		else { erro_nome.setVisible(false); }
		
		// NUMERO NO SOBRENOME
		if (!sobrenome_registro.getText().matches("[a-zA-ZÀ-ÿ]+")) {
			   tremer(sobrenome_registro);
			   tremer(sobrenome_registroTXT);
			   tremer(barra_3);
			   erro_sobrenome.setVisible(true); return; }
		else { erro_sobrenome.setVisible(false); }
		
	
	    if(!email_registro.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			   tremer(email_registro);
			   tremer(email_registroTXT);
			   tremer(barra_4);
			   erro_email.setVisible(true); return; }
		else { erro_email.setVisible(false); }
		
		//SENHAS MENORES QUE 6 DIGITOS
		if(senha_registro.getText().length() < 6 || confirme_registro.getText().length() < 6) {
			   tremer(senha_registro);
			   tremer(senha_registroTXT);
			   tremer(barra_5);
			   erro_senha.setVisible(true);	return;}
		else { erro_senha.setVisible(false); }
		
		boolean Maiuscula = senha_registro.getText().matches(".*[A-Z].*");
		boolean Minuscula = senha_registro.getText().matches(".*[a-z].*");
		boolean Numero = senha_registro.getText().matches(".*\\d.*");
		boolean CaracEspecial = senha_registro.getText().matches(".*[@#$%^&+=!].*");
		
		if (!Maiuscula || !Minuscula || !Numero || !CaracEspecial) {
			   tremer(senha_registro);
			   tremer(senha_registroTXT);
			   tremer(barra_5);
			   erro_senha.setVisible(true); return;}
		else { erro_senha.setVisible(false); }
		
		//SENHAS NÃO SÃO IGUAIS
		if(!senha_registro.getText().equals(confirme_registro.getText())) {
			   tremer(confirme_registro);
			   tremer(confirme_registroTXT);
			   tremer(barra_6);
			   erro_confirma.setVisible(true);  return;}
		else { erro_confirma.setVisible(false); }
		
	}
		
	//CADASTRO COM SUCESSO + LOG
	String CPF = cpf_registro.getText().toUpperCase();
	String NOME = nome_registro.getText().toUpperCase();
	String SOBRENOME = sobrenome_registro.getText().toUpperCase();
	String SENHA = senha_registro.getText();
	String EMAIL = email_registro.getText().toUpperCase();
		
	Client novoCliente = new Client(CPF,NOME,SOBRENOME,SENHA,EMAIL);
	
	this.cliente = novoCliente;
	
	dao.abrir().adcionar(novoCliente).fechar();
	
	try {
	    FXMLLoader loader = new FXMLLoader(
	        getClass().getResource("/application/Inicio/Scr_Confirmacao.fxml")
	    );
	
	    Parent root = loader.load();
	
	    Stage novaJanela = new Stage();
	    novaJanela.initModality(Modality.APPLICATION_MODAL);
	
	    novaJanela.setScene(new Scene(root));
	    novaJanela.showAndWait();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	TransicaoTela("/application/Inicio/Scr_Inicial.fxml",btn_registro);
	}
	

//TRANSFERENCIAS
public void Validarpix()  throws IOException {
	
  if(btn_confirmardestino.isPressed()) {
	
    //NENHUM PIX INSERIDO
    if(field_inserirpix.getText().isEmpty()) {
    	erro_pixvazio.setVisible(true);
    	tremer(field_inserirpix);
    	tremer(btn_confirmardestino);
    	tremer(erro_pixvazio); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); erro_pixparasi.setVisible(false); return;  }		  
    else { erro_pixvazio.setVisible(false); }
	  
    //NENHUM OPÇÃO SELECIONADA
    if(!radio_cpfpix.isSelected() && !radio_telefonepix.isSelected() && !radio_emailpix.isSelected() && !radio_emailpix.isSelected()) {
    	erro_opcaovazia.setVisible(true);
    	tremer(field_inserirpix);
		tremer(btn_confirmardestino);
		tremer(erro_opcaovazia); erro_pixvazio.setVisible(false); erro_pixinexistente.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
    else { erro_opcaovazia.setVisible(false); }  
  }
	 
  //CPF PIX SELECIONADO
  if(radio_cpfpix.isSelected()) {
    DAO<Client> dao = new DAO<>(Client.class);
	cliente_pix = dao.BuscarPixCPF(field_inserirpix.getText()); 
	   
  //SE NÃO EXISTIR CHAVE PIX
  if(cliente_pix == null) {
	  erro_pixinexistente.setVisible(true);
	  tremer(field_inserirpix);
	  tremer(btn_confirmardestino);
	  tremer(erro_pixinexistente); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
  else { erro_pixinexistente.setVisible(false); }   
	 
  //TENTAR ENVIAR PIX PARA SI MESMO
  if(field_inserirpix.getText().equals(cliente.getPix_cpf())) {
	  erro_pixparasi.setVisible(true);
	  tremer(field_inserirpix);
	  tremer(btn_confirmardestino);
	  tremer(erro_pixparasi); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); return; }		  
  else { erro_pixparasi.setVisible(false); }   
	  
  String cpfcliente = cliente_pix.getCpf();
  cpftexto = "**-" + cpfcliente.substring(3, 6) + "***.***";
  nometexto = cliente_pix.getNome() + " " + cliente_pix.getSobrenome();
  chavetexto = cliente_pix.getPix_cpf();
	  
  TransicaoPix();
  }
	  
	  //EMAIL PIX SELECIONADO
	  if(radio_emailpix.isSelected()) {
		  
		DAO<Client> dao = new DAO<>(Client.class);
		cliente_pix = dao.BuscarPixEMAIL(
		field_inserirpix.getText()
		); 
		   
	    //EMAIL INVALIDO
	    if(!field_inserirpix.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
	    	erro_emailinvalido.setVisible(true);
			tremer(field_inserirpix);
			tremer(btn_confirmardestino);
			tremer(erro_emailinvalido); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); erro_pixinexistente.setVisible(false);return; }		  
		else { erro_emailinvalido.setVisible(false); }   
	    
	    //SE NÃO EXISTIR CHAVE PIX
		if(cliente_pix == null) {
			erro_pixinexistente.setVisible(true);
			tremer(field_inserirpix);
			tremer(btn_confirmardestino);
			tremer(erro_pixinexistente); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); erro_emailinvalido.setVisible(false); return; }		  
		else { erro_pixinexistente.setVisible(false); }   
	    
		
	    //TENTAR ENVIAR PIX PARA SI MESMO
		if(field_inserirpix.getText().equals(cliente.getPix_email())) {
			erro_pixparasi.setVisible(true);
			tremer(field_inserirpix);
			tremer(btn_confirmardestino);
			tremer(erro_pixparasi); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); erro_emailinvalido.setVisible(false); return; }		  
		else { erro_pixparasi.setVisible(false); }   
		  
		TransicaoPix();
	  }
	  
	  if(radio_telefonepix.isSelected()) {
		  DAO<Client> dao = new DAO<>(Client.class);
		   cliente_pix = dao.BuscarPixTELEFONE(
			  field_inserirpix.getText()
		   ); 
		   
		  //SE NÃO EXISTIR CHAVE PIX
		  if(cliente_pix == null) {
			  erro_pixinexistente.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixinexistente); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
		  else { erro_pixinexistente.setVisible(false); }   
		 
		  //TENTAR ENVIAR PIX PARA SI MESMO
		  if(field_inserirpix.getText().equals(cliente.getPix_celular())) {
			  erro_pixparasi.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixparasi); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); return; }		  
		  else { erro_pixparasi.setVisible(false); }   
		  
		  String cpfcliente = cliente_pix.getCpf();
		  cpftexto = "**-" + cpfcliente.substring(3, 6) + "***.***";
		  nometexto = cliente_pix.getNome() + " " + cliente_pix.getSobrenome();
		  chavetexto = cliente_pix.getPix_cpf();
		  
		  TransicaoPix();  
	  }
	  
	  if(radio_aleatoriapix.isSelected()) {
		  DAO<Client> dao = new DAO<>(Client.class);
		   cliente_pix = dao.BuscarPixALEATORIO(
			  field_inserirpix.getText()
		   ); 
		   
		  //SE NÃO EXISTIR CHAVE PIX
		  if(cliente_pix == null) {
			  erro_pixinexistente.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixinexistente); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
		  else { erro_pixinexistente.setVisible(false); }   
		 
		  //TENTAR ENVIAR PIX PARA SI MESMO
		  if(field_inserirpix.getText().equals(cliente.getPix_aleatorio())) {
			  erro_pixparasi.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixparasi); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); return; }		  
		  else { erro_pixparasi.setVisible(false); }   
		  
		  String cpfcliente = cliente_pix.getCpf();
		  cpftexto = "**-" + cpfcliente.substring(3, 6) + "***.***";
		  nometexto = cliente_pix.getNome() + " " + cliente_pix.getSobrenome();
		  chavetexto = cliente_pix.getPix_cpf();
		  
		  TransicaoPix();  
	  }
	}
	
	public void Pixfavorito() throws IOException {
	 if(btn_favadd1.isPressed()) {
		 DAO<Client> dao = new DAO<>(Client.class);
	
		 cliente_pix = dao.BuscarPorId(cliente.getFavorito1());
		 
		 tipo = cliente.getFavorito1tipo();
	 }
	 
	 if(btn_favadd2.isPressed()) {
		 DAO<Client> dao = new DAO<>(Client.class);
		 
		 cliente_pix = dao.BuscarPorId(cliente.getFavorito2());
		 tipo = cliente.getFavorito2tipo();
	 }
	 
	 if(btn_favadd3.isPressed()) {
		 DAO<Client> dao = new DAO<>(Client.class);
		 
		 cliente_pix = dao.BuscarPorId(cliente.getFavorito3());
		 tipo = cliente.getFavorito3tipo();
	 }
	 
	 if(btn_favadd4.isPressed()) {
		 DAO<Client> dao = new DAO<>(Client.class);
		 
		 cliente_pix = dao.BuscarPorId(cliente.getFavorito4());
		 tipo = cliente.getFavorito4tipo();
	 }
	 
	 if(btn_favadd5.isPressed()) {
		 DAO<Client> dao = new DAO<>(Client.class);
		 
		 cliente_pix = dao.BuscarPorId(cliente.getFavorito5());
		 this.tipo = cliente.getFavorito5tipo();
	 }
	 
	 if(btn_favadd6.isPressed()) {
		 DAO<Client> dao = new DAO<>(Client.class);
		 
		 cliente_pix = dao.BuscarPorId(cliente.getFavorito6());
		 this.tipo = cliente.getFavorito6tipo();
	 }
	 
	 if(btn_favadd7.isPressed()) {
		 DAO<Client> dao = new DAO<>(Client.class);
		 
		 cliente_pix = dao.BuscarPorId(cliente.getFavorito7());
		 this.tipo = cliente.getFavorito7tipo();
	 }
	 
	 TransicaoPix();
	}
	
public void Favoritar() {
	  //NENHUM PIX INSERIDO
	  if(field_inserirpix.getText().isEmpty()) {
		  erro_pixvazio.setVisible(true);
		  tremer(field_inserirpix);
		  tremer(btn_confirmardestino);
		  tremer(erro_pixvazio); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); erro_pixparasi.setVisible(false); return;  }		  
	  else { erro_pixvazio.setVisible(false); }
		  
	  //NENHUM OPÇÃO SELECIONADA
	  if(!radio_cpfpix.isSelected() && !radio_telefonepix.isSelected() && !radio_emailpix.isSelected() && !radio_emailpix.isSelected()) {
		  erro_opcaovazia.setVisible(true);
		  tremer(field_inserirpix);
		  tremer(btn_confirmardestino);
		  tremer(erro_opcaovazia); erro_pixvazio.setVisible(false); erro_pixinexistente.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
	  else { erro_opcaovazia.setVisible(false); }  
		  	
		
	  if(radio_cpfpix.isSelected() && !field_inserirpix.getText().isEmpty()) {
		  
		   DAO<Client> dao = new DAO<>(Client.class);
		   cliente_pix = dao.BuscarPixCPF(
			  field_inserirpix.getText()
		   );
		   
	  if(cliente_pix == null) {
		  erro_pixinexistente.setVisible(true);
		  tremer(field_inserirpix);
		  tremer(btn_confirmardestino);
		  tremer(erro_pixinexistente); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
	  else { erro_pixinexistente.setVisible(false); }   
	  
	  if(field_inserirpix.getText().equals(cliente.getPix_cpf())) {
		  erro_pixparasi.setVisible(true);
		  tremer(field_inserirpix);
		  tremer(btn_confirmardestino);
		  tremer(erro_pixparasi); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); return; }		  
	  else { erro_pixparasi.setVisible(false); }
	  
	  cliente.adicionarFavorito(cliente_pix.getId(),1);
	  
	  dao.abrir();
	  dao.atualizar(cliente);
	  dao.fechar();
	  
	  atualizarFavoritos();
	  }
	  
	  if(radio_emailpix.isSelected() && !field_inserirpix.getText().isEmpty()) {
		  
		  DAO<Client> dao = new DAO<>(Client.class);
		  cliente_pix = dao.BuscarPixEMAIL(
				  field_inserirpix.getText()
				  );
		  
		  if(cliente_pix == null) {
			  erro_pixinexistente.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixinexistente); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
		  else { erro_pixinexistente.setVisible(false); }   
		  
		  if(field_inserirpix.getText().equals(cliente.getPix_cpf())) {
			  erro_pixparasi.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixparasi); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); return; }		  
		  else { erro_pixparasi.setVisible(false); }
		  
		  cliente.adicionarFavorito(cliente_pix.getId(), 2);
		  
		  dao.abrir();
		  dao.atualizar(cliente);
		  dao.fechar();
		  
		  atualizarFavoritos();
	  }
	  
	  if(radio_telefonepix.isSelected() && !field_inserirpix.getText().isEmpty()) {
		  
		  DAO<Client> dao = new DAO<>(Client.class);
		  cliente_pix = dao.BuscarPixTELEFONE(
				  field_inserirpix.getText()
				  );
		  
		  if(cliente_pix == null) {
			  erro_pixinexistente.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixinexistente); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
		  else { erro_pixinexistente.setVisible(false); }   
		  
		  if(field_inserirpix.getText().equals(cliente.getPix_cpf())) {
			  erro_pixparasi.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixparasi); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); return; }		  
		  else { erro_pixparasi.setVisible(false); }
		  
		  cliente.adicionarFavorito(cliente_pix.getId(), 3);
		  
		  dao.abrir();
		  dao.atualizar(cliente);
		  dao.fechar();
		  
		  atualizarFavoritos();
	  }
	  
	  if(radio_aleatoriapix.isSelected() && !field_inserirpix.getText().isEmpty()) {
		  
		  DAO<Client> dao = new DAO<>(Client.class);
		  cliente_pix = dao.BuscarPixALEATORIO(
				  field_inserirpix.getText()
				  );
		  
		  if(cliente_pix == null) {
			  erro_pixinexistente.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixinexistente); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixparasi.setVisible(false); return; }		  
		  else { erro_pixinexistente.setVisible(false); }   
		  
		  if(field_inserirpix.getText().equals(cliente.getPix_cpf())) {
			  erro_pixparasi.setVisible(true);
			  tremer(field_inserirpix);
			  tremer(btn_confirmardestino);
			  tremer(erro_pixparasi); erro_pixvazio.setVisible(false); erro_opcaovazia.setVisible(false); erro_pixinexistente.setVisible(false); return; }		  
		  else { erro_pixparasi.setVisible(false); }
		  
		  cliente.adicionarFavorito(cliente_pix.getId(), 4);
		  
		  dao.abrir();
		  dao.atualizar(cliente);
		  dao.fechar();
		  
		  atualizarFavoritos();
	  }
	  
	  
	  
	}
	
@FXML
private void RealizarPix() {

  //CAMPO VAZIO
  if(field_valorpix.getText().isEmpty()) {
    erro_opcaovazia2.setVisible(true);
	tremer(field_valorpix);
	tremer(btn_confirmarpix);
	tremer(erro_opcaovazia2); erro_saldoinsuficiente.setVisible(false); erro_valorinvalido.setVisible(false); erro_letranopix.setVisible(false); return;  }		  
  else { erro_opcaovazia2.setVisible(false); }  
  
  //LETRA NO FIELD VALOR
  if(!field_valorpix.getText().matches("^\\d+(\\.\\d{1,2})?$")) {
	erro_letranopix.setVisible(true);
	tremer(field_valorpix);
	tremer(btn_confirmarpix);
	tremer(erro_letranopix); erro_saldoinsuficiente.setVisible(false); erro_valorinvalido.setVisible(false); erro_opcaovazia2.setVisible(false); return;  }		  
  else { erro_letranopix.setVisible(false); }  
	  
  //LETRA NO FIELD VALOR
  if(Double.parseDouble(field_valorpix.getText()) <= 0.0) {
	erro_valorinvalido.setVisible(true);
	tremer(field_valorpix);
	tremer(btn_confirmarpix);
	tremer(erro_valorinvalido); erro_saldoinsuficiente.setVisible(false); erro_opcaovazia2.setVisible(false); erro_letranopix.setVisible(false); return;  }		  
  else { erro_valorinvalido.setVisible(false); }  
	  
  //LETRA NO FIELD VALOR
  if(Double.parseDouble(field_valorpix.getText()) > cliente.getSaldo()) {
	erro_saldoinsuficiente.setVisible(true);
	tremer(field_valorpix);
	tremer(btn_confirmarpix);
	tremer(erro_saldoinsuficiente); erro_opcaovazia2.setVisible(false); erro_valorinvalido.setVisible(false); erro_letranopix.setVisible(false); return;  }		  
  else { erro_saldoinsuficiente.setVisible(false); }  
	  
  ChamarconfirmacaoPIX();
  if(confirmarsenhaPIX()) { 
	  
    double saldonovo_clienteatual = cliente.getSaldo() - Double.parseDouble(field_valorpix.getText());
	
	double saldonovo_clientedestino = cliente_pix.getSaldo() + Double.parseDouble(field_valorpix.getText());
	
	cliente.setSaldo(saldonovo_clienteatual);
	cliente_pix.setSaldo(saldonovo_clientedestino);
		    
	DAO<Client> dao = new DAO<>(Client.class);
	
	dao.abrir();
	dao.atualizar(cliente);
	dao.atualizar(cliente_pix);
	dao.fechar();
	
	pix_realizado();
  }
}
	
@FXML
public boolean confirmarsenhaPIX() {
		
 //SENHA VAZIA
 if(field_senhapix.getText().isEmpty()) {
	 erro_senhapixvazia.setVisible(true);
	 tremer(field_senhapix);
	 tremer(senha_pixTXT);
	 tremer(barra_9); erro_senhapix1.setVisible(false); erro_senhapix2.setVisible(false); erro_confirmarsenhavazio.setVisible(false); return false; }		  
 else { erro_senhapixvazia.setVisible(false); }  	
		
 //CONFIRMAR SENHA VAZIO
 if(field_confirmesenhapix.getText().isEmpty()) {
	 erro_confirmarsenhavazio.setVisible(true);
	 tremer(confirme_pixTXT);
	 tremer(field_confirmesenhapix);
	 tremer(barra_10); erro_senhapix1.setVisible(false); erro_senhapix2.setVisible(false); erro_senhapixvazia.setVisible(false); return false;   }		  
 else { erro_confirmarsenhavazio.setVisible(false); }  	
 
 //SENHA ERRADA
 if(!field_senhapix.getText().equals(cliente.getSenha())) {
	 erro_senhapix1.setVisible(true);
	 tremer(senha_pixTXT);
	 tremer(field_senhapix);
	 tremer(barra_9); erro_confirmarsenhavazio.setVisible(false); erro_senhapix2.setVisible(false); erro_senhapixvazia.setVisible(false); return false;  }		  
 else { erro_senhapix1.setVisible(false); }  	
 
 //CONFIRMAR SENHA VAZIO
 if(!field_senhapix.getText().equals(field_confirmesenhapix.getText())) {
	 erro_senhapix2.setVisible(true);
	 tremer(confirme_pixTXT);
	 tremer(field_confirmesenhapix);
	 tremer(barra_10); erro_confirmarsenhavazio.setVisible(false); erro_senhapix1.setVisible(false); erro_senhapixvazia.setVisible(false); return false;  }		  
 else { erro_senhapix2.setVisible(false); } 
		
		
 Stage stage = (Stage) field_senhapix.getScene().getWindow();
 stage.close();
 return true;
}
	
public void pix_realizado() {
	try {
		FXMLLoader loader = new FXMLLoader(
		getClass().getResource("/application/Inicio/Scr_Confirmacao2.fxml")
		);
	
		Parent root = loader.load();
		
		Stage novaJanela = new Stage();
		novaJanela.initModality(Modality.APPLICATION_MODAL);
			
		novaJanela.setScene(new Scene(root));
		novaJanela.showAndWait();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	
public void confirmaralteração() {
	try {
		FXMLLoader loader = new FXMLLoader(
		getClass().getResource("/application/Inicio/Scr_Confirmacao4.fxml")
		);
			
		Parent root = loader.load();
			
		Stage novaJanela = new Stage();
		novaJanela.initModality(Modality.APPLICATION_MODAL);
			
		novaJanela.setScene(new Scene(root));
		novaJanela.showAndWait();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	
public void ChamarconfirmacaoPIX() {
	try {
		FXMLLoader loader = new FXMLLoader(
		getClass().getResource("/application/Inicio/Scr_SenhaPix.fxml")
		);
		loader.setController(this);
		Parent root = loader.load();
		
		Stage novaJanela = new Stage();
		novaJanela.initModality(Modality.APPLICATION_MODAL);
			
		
		novaJanela.setScene(new Scene(root));
		novaJanela.showAndWait();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	
public void alterarsenha() {
	  
  //CASO NENHUM DADO PREENCHIDO.
  if(field_senhanova.getText().isEmpty() || field_senhaantiga.getText().isEmpty() || field_confirmarnova.getText().isEmpty()) {
	  erroalterar_1.setVisible(true);
	  tremer(txt_senhanova1);
	  tremer(field_senhaantiga);
	  tremer(retangulo_1);
	  tremer(emoji_1);
	  tremer(barra_senha1);
	  erroalterar_2.setVisible(false); erroalterar_3.setVisible(false); erroalterar_4.setVisible(false); 
	  erroalterar_6.setVisible(false); erroalterar_5.setVisible(false); return; }
  else { erroalterar_1.setVisible(false); }
	  
  //CASO RADIOBUTTON NÃO SELECIONADO
  if(!btn_ciente_senhanova.isSelected()) {
	  erroalterar_6.setVisible(true);
	  tremer(btn_ciente_senhanova);
	  erroalterar_2.setVisible(false); erroalterar_3.setVisible(false); erroalterar_4.setVisible(false); 
	  erroalterar_1.setVisible(false); erroalterar_5.setVisible(false); return; }
  else { erroalterar_6.setVisible(false); }
	 
	  
  //CASO SENHA ATUAL ESTEJA ERRADA.
  if(!field_senhaantiga.getText().equals(cliente.getSenha())) {
	  erroalterar_3.setVisible(true);
	  tremer(txt_senhanova1);
	  tremer(field_senhaantiga);
	  tremer(retangulo_1);
	  tremer(emoji_1);
	  tremer(barra_senha1);
	  erroalterar_1.setVisible(false); erroalterar_2.setVisible(false); erroalterar_4.setVisible(false); 
	  erroalterar_6.setVisible(false); erroalterar_5.setVisible(false); return; }
  else { erroalterar_3.setVisible(false); }
	  
  //CASO SENHA ATUAL IGUAL SENHA NOVA.
  if(field_senhaantiga.getText().equals(field_senhanova.getText())) {
	  erroalterar_2.setVisible(true);
	  tremer(txt_senhanova2);
	  tremer(field_senhanova);
	  tremer(retangulo_2);
	  tremer(emoji_2);
	  tremer(barra_senha2);
	  erroalterar_1.setVisible(false); erroalterar_3.setVisible(false); erroalterar_4.setVisible(false); 
	  erroalterar_6.setVisible(false); erroalterar_5.setVisible(false); return; }
  else { erroalterar_2.setVisible(false); }
	  
  //CASO A SENHA NOVA NÃO CUMPRE REGRAS.
  if(field_senhanova.getText().length() < 6) {
	  erroalterar_4.setVisible(true);
	  tremer(txt_senhanova2);
	  tremer(field_senhanova);
	  tremer(retangulo_2);
	  tremer(emoji_2);
	  tremer(barra_senha2);
	  erroalterar_1.setVisible(false); erroalterar_3.setVisible(false); erroalterar_2.setVisible(false); 
	  erroalterar_6.setVisible(false); erroalterar_2.setVisible(false); return; }
  else { erroalterar_4.setVisible(false); }
		
  boolean Maiuscula = field_senhanova.getText().matches(".*[A-Z].*");
  boolean Minuscula = field_senhanova.getText().matches(".*[a-z].*");
  boolean Numero = field_senhanova.getText().matches(".*\\d.*");
  boolean CaracEspecial = field_senhanova.getText().matches(".*[@#$%^&+=!].*");
		
  if (!Maiuscula || !Minuscula || !Numero || !CaracEspecial) {
	  erroalterar_4.setVisible(true);
	  tremer(txt_senhanova2);
	  tremer(field_senhanova);
	  tremer(retangulo_2);
	  tremer(emoji_2);
	  tremer(barra_senha2);
	  erroalterar_1.setVisible(false); erroalterar_3.setVisible(false); erroalterar_2.setVisible(false); 
	  erroalterar_6.setVisible(false); erroalterar_2.setVisible(false); return; }
  else { erroalterar_4.setVisible(false); }
	  
  //CASO SENHA NOVA NÃO É IGUAL CONFIRMAÇÃO.
  if(!field_senhanova.getText().equals(field_confirmarnova.getText())) {
	  erroalterar_5.setVisible(true);
	  tremer(txt_senhanova3);
	  tremer(field_confirmarnova);
	  tremer(retangulo_3);
	  tremer(emoji_3);
	  tremer(barra_senha3);
	  erroalterar_1.setVisible(false); erroalterar_3.setVisible(false); erroalterar_4.setVisible(false); 
	  erroalterar_6.setVisible(false); erroalterar_2.setVisible(false); return; }
  else { erroalterar_5.setVisible(false); }
	  
  DAO<Client> dao = new DAO<>(Client.class);
  cliente.setSenha(field_senhanova.getText());
  dao.abrir();
  dao.atualizar(cliente);
  dao.fechar();
	  
  confirmaralteração();
	  
}
	
public void chamaralteracaopix2() {
	try {
		FXMLLoader loader = new FXMLLoader(
		getClass().getResource("/application/Inicio/Scr_SenhaPix3.fxml")
		);
		loader.setController(this);
		Parent root = loader.load();
			
		Stage novaJanela = new Stage();
		novaJanela.initModality(Modality.APPLICATION_MODAL);
			
		novaJanela.setScene(new Scene(root));
		novaJanela.showAndWait();
	} catch (Exception e) {
		e.printStackTrace();
	}	
}
	
public void chamaralteracaopix3() {
	try {
		FXMLLoader loader = new FXMLLoader(
		   getClass().getResource("/application/Inicio/Scr_SenhaPix4.fxml")
		);
		loader.setController(this);
		Parent root = loader.load();
		
		Stage novaJanela = new Stage();
		novaJanela.initModality(Modality.APPLICATION_MODAL);
		
		novaJanela.setScene(new Scene(root));
		novaJanela.showAndWait();
    } catch (Exception e) {
    	e.printStackTrace();
	}	
}
	
public void confirmaralteracaopix_cpf() {
	DAO<Client> dao = new DAO<>(Client.class);
	cliente.setPix_cpf(cliente.getCpf());
	dao.abrir().atualizar(cliente).fechar();
	atualizarchaves();
	
	Stage stage = (Stage) btn_sairok.getScene().getWindow();
	stage.close();	
}
	
public void confirmaralteracaopix_aleatoria() {
	DAO<Client> dao = new DAO<>(Client.class);
	String chaveAleatoria = UUID.randomUUID().toString().replace("-", "");
	
	cliente.setPix_aleatorio(chaveAleatoria);
	dao.abrir().atualizar(cliente).fechar();
	atualizarchaves();
	
	Stage stage = (Stage) btn_sairok.getScene().getWindow();
	stage.close();	
}
	
public void transicaopix_celular() {
	PIX_OP = 1;
	try {
		FXMLLoader loader = new FXMLLoader(
		getClass().getResource("/application/Inicio/Scr_SenhaPix2.fxml")
		);
		loader.setController(this);
		Parent root = loader.load();
		
		Stage novaJanela = new Stage();
		novaJanela.initModality(Modality.APPLICATION_MODAL);
		
		novaJanela.setScene(new Scene(root));
		novaJanela.showAndWait();
	} catch (Exception e) {
		e.printStackTrace();
	}	
}
	
public void transicaopix_email() {
	PIX_OP = 2;
	try {
		FXMLLoader loader = new FXMLLoader(
		getClass().getResource("/application/Inicio/Scr_SenhaPix2.fxml")
		);
		loader.setController(this);
		Parent root = loader.load();
			
		Stage novaJanela = new Stage();
		novaJanela.initModality(Modality.APPLICATION_MODAL);
			
		novaJanela.setScene(new Scene(root));
		novaJanela.showAndWait();
	} catch (Exception e) {
		e.printStackTrace();
	}	
}
	
public void alteracaopix() {
	
DAO<Client> dao = new DAO<>(Client.class);
		
	//CAMPOS VAZIOS	
	if (field_chavepix.getText().isEmpty() || field_confirmechave.getText().isEmpty()) {
		erro_alterarpix1.setVisible(true);
		tremer(field_chavepix);
		tremer(chave_pixTXT);
		tremer(barra_9);
		erro_alterarpix2.setVisible(false); erro_alterarpix3.setVisible(false); erro_alterarpix4.setVisible(false); 
		erro_alterarpix5.setVisible(false);      
		return; }
	else { erro_alterarpix1.setVisible(false); } 
	 	 
	//1 É O CELULAR
	if(PIX_OP == 1) {
		
	 // LETRA NO CPF
	 if (!field_chavepix.getText().matches("[0-9]+")) {
		 erro_alterarpix3.setVisible(true);
		 tremer(field_chavepix);
		 tremer(chave_pixTXT);
		 tremer(barra_9);
		 erro_alterarpix2.setVisible(false); erro_alterarpix1.setVisible(false); erro_alterarpix4.setVisible(false); 
		 erro_alterarpix5.setVisible(false);
		 return; }
	 else { erro_alterarpix3.setVisible(false); } 
	   
	 // NUMERO COM MENOS OU MAIS DE 11 DIGITOS
	 if (field_chavepix.getText().length() != 11) {
		 erro_alterarpix4.setVisible(true);
		 tremer(field_chavepix);
		 tremer(chave_pixTXT);
		 tremer(barra_9);
		 erro_alterarpix2.setVisible(false); erro_alterarpix3.setVisible(false); erro_alterarpix1.setVisible(false); 
		 erro_alterarpix5.setVisible(false);
		 return; }
	 else { erro_alterarpix4.setVisible(false); } 
	   
	 //CHAVES NÃO CONDIZEM	
	 if (!field_chavepix.getText().equals(field_confirmechave.getText())) {
		 erro_alterarpix2.setVisible(true);
		 tremer(field_chavepix);
		 tremer(chave_pixTXT);
		 tremer(barra_9);
		 tremer(field_confirmechave);
		 tremer(confirmar_pixTXT);
		 tremer(barra_10);
		 erro_alterarpix1.setVisible(false); erro_alterarpix3.setVisible(false); erro_alterarpix4.setVisible(false); 
		 erro_alterarpix5.setVisible(false);	
		 return; }
	 else { erro_alterarpix2.setVisible(false); } 
	   
	 cliente.setPix_celular(field_chavepix.getText());
	 dao.abrir().atualizar(cliente).fechar();
	 atualizarchaves();
	   
	 Stage stage = (Stage) btn_sairok.getScene().getWindow();
	 stage.close();
}
	 
	 //2 É O EMAIL
	 if(PIX_OP == 2) {
		 // EMAIL INVALIDO
		 if (!field_chavepix.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			 erro_alterarpix5.setVisible(true);
			 tremer(field_confirmechave);
			 tremer(confirmar_pixTXT);
			 tremer(barra_10);
			 erro_alterarpix2.setVisible(false); erro_alterarpix3.setVisible(false); erro_alterarpix1.setVisible(false); 
			 erro_alterarpix4.setVisible(false); 
			 return; }
		 else { erro_alterarpix5.setVisible(false); } 
		 
		 //CHAVES NÃO CONDIZEM	
		 if (!field_chavepix.getText().equals(field_confirmechave.getText())) {
			  erro_alterarpix2.setVisible(true);
			  tremer(field_confirmechave);
			  tremer(confirmar_pixTXT);
			  tremer(barra_10);
			  erro_alterarpix1.setVisible(false); erro_alterarpix3.setVisible(false); erro_alterarpix4.setVisible(false); 
			  erro_alterarpix5.setVisible(false);	
			  return; }
		 else { erro_alterarpix2.setVisible(false); }
		 
		 cliente.setPix_email(field_chavepix.getText());
		   
		 dao.abrir().atualizar(cliente).fechar();
		 atualizarchaves();
		 
	     Stage stage = (Stage) btn_sairok.getScene().getWindow();
	     stage.close();
	 }  
}
	
public void ExcluirConta() {
  DAO<Client> dao = new DAO<>(Client.class);
	
  if(radio_concordarexcluir.isSelected() && btn_confirmar_excluirconta.isPressed()) {
	 
   ChamarconfirmacaoPIX();
   if(confirmarsenhaPIX() == true) {
	   
	 for (Client c : dao.listarTodos()) {
	   if (c.removerFavorito(cliente.getId(), 1)) {
         dao.atualizar(c);
	   }
	 }
				
	 dao.abrir();
	 dao.excluir(cliente);
	 dao.fechar();
				
	 try {
		 FXMLLoader loader = new FXMLLoader(
		 getClass().getResource("/application/Inicio/Scr_Confirmacao3.fxml")
		 );
	
		 Parent root = loader.load();
				
		 Stage novaJanela = new Stage();
		 novaJanela.initModality(Modality.APPLICATION_MODAL);
				
		 novaJanela.setScene(new Scene(root));
		 novaJanela.showAndWait();
	 } catch (Exception e) {
		 e.printStackTrace();
	 }
				
	 System.exit(0);
    }
  }
}

//OUTROS
public void FecharPrograma() {
	try {
		FXMLLoader loader = new FXMLLoader(
		getClass().getResource("/application/Inicio/Scr_Sair.fxml")
		);
	
		Parent root = loader.load();
	
		Stage novaJanela = new Stage();
		novaJanela.initModality(Modality.APPLICATION_MODAL);
		        
		novaJanela.setScene(new Scene(root));
		novaJanela.showAndWait();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	
public void Sair_NaoSair() {
 if(btn_sairsim != null && btn_sairsim.isPressed()) {
    System.exit(0);
 }
	  
 if(btn_sairnao != null && btn_sairnao.isPressed()) {
	 Stage stage = (Stage) btn_sairnao.getScene().getWindow();
	 stage.close();	
 }
	  
 if (btn_sairok != null && btn_sairok.isPressed()) {
	 Stage stage = (Stage) btn_sairok.getScene().getWindow();
	 stage.close();	
 }
}
}