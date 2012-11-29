/**
 * ZaMobi v.0.1 Beta 
 * @author Team
 *
 * Team :
 * M.Qusnul L.(09650014)
 * M.Syafrudin(09650026)
 * M.Shidqul A.(09650032)
 * Ismi Fitriyani(09650036)
 * M.Asfarudin(09650051) 
 * Rahma Nabila(09650053)
 * 
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ZaMobi extends MIDlet implements CommandListener {

	private Display display;
	private imageZaMobi canvas;
	private List menu;
	private String kata2;
	private Form zakatpenghasilan, dua, zakatfitrah, zakatmal, about, atur, hasilzakat;
	
	String total, status;
	TextField hrgBeras, hrgEmas, bykOrg, jmlhrta, jmlgaji, kebutuhan;
	Command keluarCmd = new Command ("Keluar",Command.EXIT,1);
	Command prosesCmd = new Command ("Proses",Command.OK,2);
	Command hitung2 = new Command ("OK", Command.OK,1);
	Command hitung1 = new Command ("Hitung", Command.OK,2);
	Command kembaliCmd = new Command ("Kembali",Command.BACK,2);
	Command pilihCmd = new Command ("Pilih", Command.OK, 0);
	Command Simpan = new Command ("Simpan", Command.BACK, 2);

	public ZaMobi(){
		display = Display.getDisplay(this);
		canvas = new imageZaMobi(this);
	}
	protected void startApp() {
		display.setCurrent(canvas);		
	}
	
	protected void pauseApp() { }
	
	protected void destroyApp(boolean unconditional) { }

	//form menu
	public List getMenu(){
		if(menu==null){
			menu = new List("Menu Utama", Choice.IMPLICIT);
			menu.append("Zakat Fitrah", null);
			menu.append("Zakat Maal", null);
			menu.append("Zakat Penghasilan", null);
			menu.append("Atur Harga", null);
			menu.append("Tentang Kami", null);	
			menu.append("Keluar", null);	
			menu.setCommandListener(this);
			display.setCurrent(menu);
		}
		return menu;
	}	
	
	// form zakat fitrah
	public Form getZafi(){
		if (zakatfitrah==null){
			zakatfitrah = new Form ("Hitung zakat Fitrah");
			bykOrg = new TextField ("Banyak Orang : ", "0", 5, TextField.NUMERIC);
			zakatfitrah.append(bykOrg);
			zakatfitrah.addCommand(kembaliCmd);
			zakatfitrah.addCommand(hitung1);
			zakatfitrah.setCommandListener(this);
			display.setCurrent(zakatfitrah);
		}
		return zakatfitrah;
	}
	
	// form zakat maal
	public Form getZaMal(){
		if (zakatmal==null){
			zakatmal = new Form ( "Hitung zakat Maal" );
			jmlhrta = new TextField("Jumlah harta Selama 1 tahun : ", "0", 12, TextField.NUMERIC);
			zakatmal.append (jmlhrta);
			zakatmal.addCommand(kembaliCmd);
			zakatmal.addCommand(prosesCmd);
			zakatmal.setCommandListener(this);
			display.setCurrent(zakatmal);
		}
		return zakatmal;
	}
	
	// form zakat penghasilan
	public Form getZaPeng(){
		if (zakatpenghasilan==null){
			zakatpenghasilan = new Form ("Htung zakat Penghasilan");
			jmlgaji = new TextField ("Gaji Total perbulan: " , "0" , 12, TextField.NUMERIC);
			kebutuhan = new TextField ("Pengeluaran perbulan: ", "0", 12, TextField.NUMERIC);
			zakatpenghasilan.append (jmlgaji);
			zakatpenghasilan.append (kebutuhan);
			zakatpenghasilan.addCommand(kembaliCmd);
			zakatpenghasilan.addCommand(hitung2);
			zakatpenghasilan.setCommandListener(this);
			display.setCurrent(zakatpenghasilan);
		}
		return zakatpenghasilan;
	}
	
	// form atur harga emas dan beras
	public Form getAtur(){
		if (atur==null){
			atur = new Form ("Setting Harga");
			hrgEmas = new TextField ("Emas Murni per gram :", "420000", 7, TextField.NUMERIC);
			hrgBeras = new TextField ("Beras per kg :", "7000", 7, TextField.NUMERIC);
			atur.append("Masukan Harga Terkini (Update) untuk :");
			atur.append(hrgEmas);
			atur.append(hrgBeras);
			atur.addCommand(Simpan);
			atur.setCommandListener(this);
			display.setCurrent(atur);
		}
		return atur;
	}
	
	// form about
	public Form getAbout(){
		if (about==null){
			about = new Form ( "About ZaMobi v.0.1 Beta" );
			kata2 = "ZaMobi v.0.1 Beta\n\n ZaMobi adalah aplikasi penghitungan zakat via mobile. Aplikasi ini gratis, anda dapat menggunakan dan membagikannya kepada siapa saja. Untuk update kunjungi \nwww.zamobi.co.tv\n\nAplikasi ini dibuat oleh :\n  M.Qusnul L.(09650014)\n  M.Syafrudin(09650026)\n  M.Shidqul A.(09650032)\n  Ismi Fitriyani(09650036)\n  M.Asfarudin(09650051)\n  Rahma Nabila(09650053)\n\nHak Cipta Milik Allah SWT - 2011";
			about.append(kata2);
			about.addCommand(kembaliCmd);
			about.setCommandListener(this);
			display.setCurrent(about);
		}
		return about;
	}
	
	//form hasil zakat
	public Form gethasilzakat(){
		if(hasilzakat == null){
			hasilzakat = new Form ("Hasil Perhitungan Zakat");
			hasilzakat.addCommand(kembaliCmd);
			hasilzakat.setCommandListener(this);
			display.setCurrent(hasilzakat);
		}
		return hasilzakat;
	}
	
    //void utk set ke tampilan awal	
	public void awal() {
		display.setCurrent(getMenu());
	}

	//void utk keluar aplikasi
	public void keluar() {
		notifyDestroyed();
	}

	// void utk menghitung zakat fitrah
	public void hitung1(){
		hasilzakat = new Form ("Hasil Perhitungan Zakat");
		String byk, bras;
		int ibyk, ibras, ijml;
		byk=bykOrg.getString();
		bras=hrgBeras.getString();
		ibyk=Integer.parseInt(byk);
		ibras=Integer.parseInt(bras);
		ijml=ibyk*ibras*5/2;
		total="Rp "+Integer.toString(ijml);
		status="Zakat yang Harus dibayarkan sejumlah ";
		hasilzakat.append(status+ibyk*5/2+" Kg Beras Setara dengan uang "+total);
		hasilzakat.addCommand(kembaliCmd);
		hasilzakat.setCommandListener(this);
		display.setCurrent(hasilzakat);	

	}
	
	// void hitung zakat maal
	public void hitungmal() {
		hasilzakat = new Form ("Hasil Perhitungan Zakat");
		String hrta, emas, nisab;
		int ihrta, iemas, itotal, inisab;
		hrta=jmlhrta.getString();
		emas=hrgEmas.getString();
		ihrta=Integer.parseInt(hrta);
		iemas=Integer.parseInt(emas);
		inisab=iemas*640/7;
		nisab=Integer.toString(inisab);
		if (ihrta>inisab){
			itotal=ihrta*5/200;
			total="Rp "+Integer.toString(itotal);
			status="Anda Wajib Mengeluarkan ZAKAT! \nZakat yang Harus dibayarkan sejumlah ";
			hasilzakat.append(status+total);
		} else {
			status="Anda TIDAK Wajib Mengeluarkan ZAKAT!";
			hasilzakat.append(status);
			
		}
		hasilzakat.addCommand(kembaliCmd);
		hasilzakat.setCommandListener(this);
		display.setCurrent(hasilzakat);	
	}
	
	//void untuk menghitung zakat profesi
	public void hitungprof(){
		hasilzakat = new Form ("Hasil Perhitungan Zakat");
		String gaji, beras, pengeluaran, nisab;
		int igaji, iberas, ikebutuhan, itotal, inisab, iitungan;
		gaji=jmlgaji.getString();
		pengeluaran=kebutuhan.getString();
		beras=hrgBeras.getString();
		ikebutuhan=Integer.parseInt(pengeluaran);
		igaji=Integer.parseInt(gaji);
		iitungan=igaji-ikebutuhan;
		iberas=Integer.parseInt(beras);
		inisab=iberas*520;
		nisab=Integer.toString(inisab);
		if (iitungan>inisab){
			itotal=iitungan*5/200;
			total="Rp "+Integer.toString(itotal);
			status="Anda Wajib Mengeluarkan ZAKAT! \nZakat yang Harus dibayarkan sejumlah ";
			hasilzakat.append(status+total);
		} else {
			status="Anda TIDAK Wajib Mengeluarkan ZAKAT!";
			hasilzakat.append(status);
		}
		hasilzakat.addCommand(kembaliCmd);
		hasilzakat.setCommandListener(this);
		display.setCurrent(hasilzakat);
	}

	public void commandAction (Command c, Displayable d) {
		String data = c.getLabel();
		if (data == "Keluar") {
			keluar();
		} else if (data == "Hitung") {
			hitung1();
		}else if(data == "OK"){
			hitungprof();
		}else if(data == "Proses"){	
			hitungmal();
		}else if (data == "Kembali") {
			awal();
		}else if(data=="Simpan"){
			awal();
		}else if (c == List.SELECT_COMMAND){
			switch (menu.getSelectedIndex()){
				case 0: display.setCurrent(getZafi()); break;
				case 1: display.setCurrent(getZaMal()); break;
				case 2: display.setCurrent(getZaPeng()); break;
				case 3: display.setCurrent(getAtur()); break;
				case 4: display.setCurrent(getAbout()); break;
				case 5: keluar(); break;
			}
		}
	}

	class imageZaMobi extends Canvas implements CommandListener{
		private Command mulai;
		private ZaMobi midlet;
		private Image image;
		private String message = "Welcome to ZaMobi apps..";

		public imageZaMobi(ZaMobi midlet){
			this.midlet = midlet;
			mulai = new Command("Mulai", Command.OK, 1);
			addCommand(mulai);
			setCommandListener(this);
			try{
				image = Image.createImage("/zakah.gif");
				Graphics graphics = image.getGraphics();
				Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
				graphics.setFont(font);
				graphics.setColor(255, 0, 0);
				graphics.fillRoundRect(0,0, image.getWidth()-1, image.getHeight()-1, 20, 20); 
				graphics.setColor(0, 0, 255);   
				graphics.drawString(message,(image.getWidth()/2) - (font.stringWidth(message)/2), 0, Graphics.TOP | Graphics.LEFT);
			}catch (Exception e){
				System.err.println(e);
			}    
		}
	
		protected void paint(Graphics g){
			if(image != null){
				g.drawImage(image, getWidth()/2, getHeight()/2, Graphics.VCENTER | Graphics.HCENTER);
			}
		}
		public void commandAction(Command c, Displayable d){
			String label = c.getLabel();
			if(label.equals("Mulai")){
				midlet.awal();
			} 
	}
	}
}
