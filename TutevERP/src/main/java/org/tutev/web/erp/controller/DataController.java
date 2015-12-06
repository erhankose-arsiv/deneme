package org.tutev.web.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tutev.web.erp.entity.genel.KodluListe;
import org.tutev.web.erp.entity.genel.KodluListeTip;
import org.tutev.web.erp.service.DataService;
import org.tutev.web.erp.service.KodluListeService;

@Component("dataController")
@Scope("singleton")
public class DataController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2141891297862838000L;

	@Autowired
	private transient DataService dataService;
	@Autowired
	private transient KodluListeService kodluListeService;

	public static Logger logger = Logger.getLogger(DataController.class);

	private List<KodluListe> uyrukListe;
	private List<KodluListe> olcuBirimListe;
	private List<KodluListe> paraBirimListe;
	//EK
	private List<KodluListe> uretimTipiListe;
	

	@PostConstruct
	private void init() {
		dataGuncelle();
		if (uyrukListe == null || uyrukListe.size() < 1) {
			logger.debug("Referans Data Bulunamadý DB insert Yapýlýyor");
			kodluListeService.save(new KodluListe(null, "TC","Türkiye Cumhuriyeti", KodluListeTip.UYRUK));
			kodluListeService.save(new KodluListe(null, "ABD","Amerika Birleþik Devletleri", KodluListeTip.UYRUK));
			kodluListeService.save(new KodluListe(null, "FR", "Fransa",KodluListeTip.UYRUK));
			logger.debug("Referans Data Bulunamadý DB insert Yapýldý");
			dataGuncelle();
		}
		
		if (uretimTipiListe == null || uretimTipiListe.size() < 1) {
			logger.debug("uretimTipiListe Referans verisi Bulunamadý DB insert Yapýlýyor");
			kodluListeService.save(new KodluListe(null, "krtk","Kritik Üretim", KodluListeTip.URETIM_TIP));
			kodluListeService.save(new KodluListe(null, "Zrnl","Zorunlu Üretim", KodluListeTip.URETIM_TIP));
			kodluListeService.save(new KodluListe(null, "icuretim", "Ýç Kaynaklar için Üretim",KodluListeTip.URETIM_TIP));
			kodluListeService.save(new KodluListe(null, "dnmsl", "Dönemsel Üretim",KodluListeTip.URETIM_TIP));
			logger.debug("Referans Data Bulunamadý DB insert Yapýldý");
			dataGuncelle();
		}
		if (paraBirimListe == null || paraBirimListe.size() < 1) {
			logger.debug("paraBirimListe Referans verisi Bulunamadý DB insert Yapýlýyor");
			kodluListeService.save(new KodluListe(null, "TL","Türk Lirası", KodluListeTip.PARA_BIRIM));
			kodluListeService.save(new KodluListe(null, "USD","Amerikan Doları", KodluListeTip.PARA_BIRIM));
			kodluListeService.save(new KodluListe(null, "EURO", "Avrupa Para Birimi",KodluListeTip.PARA_BIRIM));
			logger.debug("Referans Data Bulunamadý DB insert Yapýldý");
			dataGuncelle();
		}	
		
	}

	public void dataGuncelle() {
		logger.info("Referans Data Yükleniyor");
		uyrukListe = dataService.getByType(KodluListeTip.UYRUK);
		olcuBirimListe = dataService.getByType(KodluListeTip.OLCU_BIRIMI);
		paraBirimListe = dataService.getByType(KodluListeTip.PARA_BIRIM);
		uretimTipiListe = dataService.getByType(KodluListeTip.URETIM_TIP);
		logger.info("Referans Data Yüklendi");
	}

	public void dataUretimGuncelle() {
		logger.info("Üretim Tip Referans Data Yükleniyor");
		uyrukListe = dataService.getByType(KodluListeTip.URETIM_TIP);
	 
		logger.info("Üretim Tip Referans Data Yüklendi");
	}
	
	public List<KodluListe> getUretimTipiListe() {
		return uretimTipiListe;
	}

	public List<KodluListe> getOlcuBirimListe() {
		return olcuBirimListe;
	}

	public List<KodluListe> getParaBirimListe() {
		return paraBirimListe;
	}

	public List<KodluListe> getUyrukListe() {
		return uyrukListe;
	}
}