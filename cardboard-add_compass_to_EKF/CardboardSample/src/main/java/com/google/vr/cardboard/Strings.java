/*     */ package com.google.vr.cardboard;
/*     */
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */
/*     */
/*     */
/*     */ public class Strings
/*     */
{
    /*     */   public static final String NO_BROWSER_TEXT = "NO_BROWSER_TEXT";
    /*     */   public static final String DIALOG_TITLE = "DIALOG_TITLE";
    /*     */   public static final String CANCEL_BUTTON = "CANCEL_BUTTON";
    /*     */   public static final String SETUP_BUTTON = "SETUP_BUTTON";
    /*     */   public static final String DIALOG_MESSAGE_NO_CARDBOARD =
        "DIALOG_MESSAGE_NO_CARDBOARD";
    /*     */   public static final String DIALOG_MESSAGE_SETUP =
        "DIALOG_MESSAGE_SETUP";
    /*     */   public static final String GO_TO_PLAYSTORE_BUTTON =
        "GO_TO_PLAYSTORE_BUTTON";
    /*  18 */   private static final Map<String, Map<String, String>> LANGUAGE_MAP =
                new HashMap();
    /*  19 */   static { Map<String, String> el = new HashMap();
                         /*  20 */     el.put("NO_BROWSER_TEXT", "Δεν βρέθηκε εφ. περιήγ. για άνοιγμα του ιστότοπου");
                         /*  21 */     el.put("DIALOG_TITLE", "Διαμόρφωση");
                         /*  22 */     el.put("CANCEL_BUTTON", "Ακύρωση");
                         /*  23 */     el.put("SETUP_BUTTON", "Ρύθμιση");
                         /*  24 */     el.put("DIALOG_MESSAGE_NO_CARDBOARD", "Αποκτήστε την εφαρμογή Cardboard για να διαμορφώσετε το σύστημα προβολής σας.");
                         /*  25 */     el.put("DIALOG_MESSAGE_SETUP", "Ρυθμίστε το σύστημα προβολής σας για τη βέλτιστη εμπειρία.");
                         /*  26 */     el.put("GO_TO_PLAYSTORE_BUTTON", "Μετάβαση στο Play Store");
                         /*  27 */     LANGUAGE_MAP.put("el", el);
                         /*     */
                         /*  29 */     Map<String, String> en = new HashMap();
                         /*  30 */     en.put("NO_BROWSER_TEXT", "No browser to open website");
                         /*  31 */     en.put("DIALOG_TITLE", "Configure");
                         /*  32 */     en.put("CANCEL_BUTTON", "Cancel");
                         /*  33 */     en.put("SETUP_BUTTON", "Setup");
                         /*  34 */     en.put("DIALOG_MESSAGE_NO_CARDBOARD", "Get the Cardboard app in order to configure your viewer.");
                         /*  35 */     en.put("DIALOG_MESSAGE_SETUP", "Set up your viewer for the best experience.");
                         /*  36 */     en.put("GO_TO_PLAYSTORE_BUTTON", "Go to Play Store");
                         /*  37 */     LANGUAGE_MAP.put("en", en);
                         /*     */
                         /*  39 */     Map<String, String> vi = new HashMap();
                         /*  40 */     vi.put("NO_BROWSER_TEXT", "Không có trình duyệt nào để mở trang web");
                         /*  41 */     vi.put("DIALOG_TITLE", "Định cấu hình");
                         /*  42 */     vi.put("CANCEL_BUTTON", "Hủy");
                         /*  43 */     vi.put("SETUP_BUTTON", "Thiết lập");
                         /*  44 */     vi.put("DIALOG_MESSAGE_NO_CARDBOARD", "Hãy tải ứng dụng Cardboard để định cấu hình thiết bị xem của bạn.");
                         /*  45 */     vi.put("DIALOG_MESSAGE_SETUP", "Thiết lập thiết bị xem của bạn để có trải nghiệm tốt nhất.");
                         /*  46 */     vi.put("GO_TO_PLAYSTORE_BUTTON", "Truy cập Cửa hàng Play");
                         /*  47 */     LANGUAGE_MAP.put("vi", vi);
                         /*     */
                         /*  49 */     Map<String, String> pt_PT = new HashMap();
                         /*  50 */     pt_PT.put("NO_BROWSER_TEXT", "Sem navegador para abrir o Website");
                         /*  51 */     pt_PT.put("DIALOG_TITLE", "Configurar");
                         /*  52 */     pt_PT.put("CANCEL_BUTTON", "Cancelar");
                         /*  53 */     pt_PT.put("SETUP_BUTTON", "Configurar");
                         /*  54 */     pt_PT.put("DIALOG_MESSAGE_NO_CARDBOARD", "Obtenha a aplicação Cardboard para configurar o seu visualizador.");
                         /*  55 */     pt_PT.put("DIALOG_MESSAGE_SETUP", "Configure o seu visualizador para obter a melhor experiência.");
                         /*  56 */     pt_PT.put("GO_TO_PLAYSTORE_BUTTON", "Ir para a Play Store");
                         /*  57 */     LANGUAGE_MAP.put("pt_PT", pt_PT);
                         /*     */
                         /*  59 */     Map<String, String> it = new HashMap();
                         /*  60 */     it.put("NO_BROWSER_TEXT", "Nessun browser per l'apertura del sito web");
                         /*  61 */     it.put("DIALOG_TITLE", "Configura");
                         /*  62 */     it.put("CANCEL_BUTTON", "Annulla");
                         /*  63 */     it.put("SETUP_BUTTON", "Imposta");
                         /*  64 */     it.put("DIALOG_MESSAGE_NO_CARDBOARD", "Scarica l'app Cardboard per configurare il tuo visore.");
                         /*  65 */     it.put("DIALOG_MESSAGE_SETUP", "Imposta il visore per un'esperienza ottimale.");
                         /*  66 */     it.put("GO_TO_PLAYSTORE_BUTTON", "Visita il Play Store");
                         /*  67 */     LANGUAGE_MAP.put("it", it);
                         /*     */
                         /*  69 */     Map<String, String> iw = new HashMap();
                         /*  70 */     iw.put("NO_BROWSER_TEXT", "אין דפדפן שיכול לפתוח את האתר");
                         /*  71 */     iw.put("DIALOG_TITLE", "הגדרה");
                         /*  72 */     iw.put("CANCEL_BUTTON", "ביטול");
                         /*  73 */     iw.put("SETUP_BUTTON", "הגדר");
                         /*  74 */     iw.put("DIALOG_MESSAGE_NO_CARDBOARD", "‏הורד את אפליקציית Cardboard כדי להגדיר את המציג.");
                         /*  75 */     iw.put("DIALOG_MESSAGE_SETUP", "הגדר את המציג לקבלת החוויה הטובה ביותר.");
                         /*  76 */     iw.put("GO_TO_PLAYSTORE_BUTTON", "‏עבור לחנות Play");
                         /*  77 */     LANGUAGE_MAP.put("iw", iw);
                         /*     */
                         /*  79 */     Map<String, String> bg = new HashMap();
                         /*  80 */     bg.put("NO_BROWSER_TEXT", "Няма браузър за отваряне на уебсайта");
                         /*  81 */     bg.put("DIALOG_TITLE", "Конфигуриране");
                         /*  82 */     bg.put("CANCEL_BUTTON", "Отказ");
                         /*  83 */     bg.put("SETUP_BUTTON", "Настройване");
                         /*  84 */     bg.put("DIALOG_MESSAGE_NO_CARDBOARD", "Изтеглете приложението Cardboard, за да конфигурирате очилата си.");
                         /*  85 */     bg.put("DIALOG_MESSAGE_SETUP", "За най-добра работа настройте очилата си.");
                         /*  86 */     bg.put("GO_TO_PLAYSTORE_BUTTON", "Към Google Play Магазин");
                         /*  87 */     LANGUAGE_MAP.put("bg", bg);
                         /*     */
                         /*  89 */     Map<String, String> es_MX = new HashMap();
                         /*  90 */     es_MX.put("NO_BROWSER_TEXT", "No hay ningún navegador para abrir el sitio web");
                         /*  91 */     es_MX.put("DIALOG_TITLE", "Configurar");
                         /*  92 */     es_MX.put("CANCEL_BUTTON", "Cancelar");
                         /*  93 */     es_MX.put("SETUP_BUTTON", "Configuración");
                         /*  94 */     es_MX.put("DIALOG_MESSAGE_NO_CARDBOARD", "Obtén la aplicación Cardboard para poder configurar el visor.");
                         /*  95 */     es_MX.put("DIALOG_MESSAGE_SETUP", "Configura el visor para obtener la mejor experiencia.");
                         /*  96 */     es_MX.put("GO_TO_PLAYSTORE_BUTTON", "Ir a Play Store");
                         /*  97 */     LANGUAGE_MAP.put("es_MX", es_MX);
                         /*     */
                         /*  99 */     Map<String, String> cs = new HashMap();
                         /* 100 */     cs.put("NO_BROWSER_TEXT", "Chybí prohlížeč k otevření webových stránek");
                         /* 101 */     cs.put("DIALOG_TITLE", "Konfigurovat");
                         /* 102 */     cs.put("CANCEL_BUTTON", "Zrušit");
                         /* 103 */     cs.put("SETUP_BUTTON", "Nastavit");
                         /* 104 */     cs.put("DIALOG_MESSAGE_NO_CARDBOARD", "Stáhněte si aplikaci Cardboard ke konfiguraci svých brýlí.");
                         /* 105 */     cs.put("DIALOG_MESSAGE_SETUP", "Nastavte si brýle, aby váš zážitek byl co nejlepší.");
                         /* 106 */     cs.put("GO_TO_PLAYSTORE_BUTTON", "Přejít do Obchodu Play");
                         /* 107 */     LANGUAGE_MAP.put("cs", cs);
                         /*     */
                         /* 109 */     Map<String, String> id = new HashMap();
                         /* 110 */     id.put("NO_BROWSER_TEXT", "Tidak ada browser untuk membuka situs web");
                         /* 111 */     id.put("DIALOG_TITLE", "Konfigurasikan");
                         /* 112 */     id.put("CANCEL_BUTTON", "Batal");
                         /* 113 */     id.put("SETUP_BUTTON", "Penyiapan");
                         /* 114 */     id.put("DIALOG_MESSAGE_NO_CARDBOARD", "Dapatkan aplikasi Cardboard untuk mengonfigurasikan penampil Anda.");
                         /* 115 */     id.put("DIALOG_MESSAGE_SETUP", "Siapkan penampil Anda untuk pengalaman terbaik.");
                         /* 116 */     id.put("GO_TO_PLAYSTORE_BUTTON", "Buka Play Store");
                         /* 117 */     LANGUAGE_MAP.put("id", id);
                         /*     */
                         /* 119 */     Map<String, String> es = new HashMap();
                         /* 120 */     es.put("NO_BROWSER_TEXT", "No tienes navegadores para abrir este sitio web");
                         /* 121 */     es.put("DIALOG_TITLE", "Configurar");
                         /* 122 */     es.put("CANCEL_BUTTON", "Cancelar");
                         /* 123 */     es.put("SETUP_BUTTON", "Configurar visor");
                         /* 124 */     es.put("DIALOG_MESSAGE_NO_CARDBOARD", "Descárgate la aplicación Cardboard para configurar tu visor.");
                         /* 125 */     es.put("DIALOG_MESSAGE_SETUP", "Configura tu visor para aprovechar todas las posibilidades de la realidad virtual.");
                         /* 126 */     es.put("GO_TO_PLAYSTORE_BUTTON", "Ir a Play Store");
                         /* 127 */     LANGUAGE_MAP.put("es", es);
                         /*     */
                         /* 129 */     Map<String, String> ru = new HashMap();
                         /* 130 */     ru.put("NO_BROWSER_TEXT", "Нет браузера для просмотра страницы");
                         /* 131 */     ru.put("DIALOG_TITLE", "Настройка");
                         /* 132 */     ru.put("CANCEL_BUTTON", "Отмена");
                         /* 133 */     ru.put("SETUP_BUTTON", "Настроить");
                         /* 134 */     ru.put("DIALOG_MESSAGE_NO_CARDBOARD", "Чтобы настроить очки, установите приложение Cardboard.");
                         /* 135 */     ru.put("DIALOG_MESSAGE_SETUP", "Для наилучших результатов настройте свои очки.");
                         /* 136 */     ru.put("GO_TO_PLAYSTORE_BUTTON", "Перейти в Play Маркет");
                         /* 137 */     LANGUAGE_MAP.put("ru", ru);
                         /*     */
                         /* 139 */     Map<String, String> nl = new HashMap();
                         /* 140 */     nl.put("NO_BROWSER_TEXT", "Geen browser om website te openen");
                         /* 141 */     nl.put("DIALOG_TITLE", "Configureren");
                         /* 142 */     nl.put("CANCEL_BUTTON", "Annuleren");
                         /* 143 */     nl.put("SETUP_BUTTON", "Instellen");
                         /* 144 */     nl.put("DIALOG_MESSAGE_NO_CARDBOARD", "Download de Cardboard-app om je bril te configureren.");
                         /* 145 */     nl.put("DIALOG_MESSAGE_SETUP", "Stel je bril in voor een optimale gebruikerservaring.");
                         /* 146 */     nl.put("GO_TO_PLAYSTORE_BUTTON", "Naar de Play Store");
                         /* 147 */     LANGUAGE_MAP.put("nl", nl);
                         /*     */
                         /* 149 */     Map<String, String> pt = new HashMap();
                         /* 150 */     pt.put("NO_BROWSER_TEXT", "Nenhum navegador encontrado para abrir o site");
                         /* 151 */     pt.put("DIALOG_TITLE", "Configurar");
                         /* 152 */     pt.put("CANCEL_BUTTON", "Cancelar");
                         /* 153 */     pt.put("SETUP_BUTTON", "Configurar");
                         /* 154 */     pt.put("DIALOG_MESSAGE_NO_CARDBOARD", "Faça o download do app do Google Cardboard para configurar seu visualizador.");
                         /* 155 */     pt.put("DIALOG_MESSAGE_SETUP", "Configure seu visualizador para ter a melhor experiência.");
                         /* 156 */     pt.put("GO_TO_PLAYSTORE_BUTTON", "Acessar a Google Play Store");
                         /* 157 */     LANGUAGE_MAP.put("pt", pt);
                         /*     */
                         /* 159 */     Map<String, String> nb = new HashMap();
                         /* 160 */     nb.put("NO_BROWSER_TEXT", "Finner ingen nettleser som kan åpne nettstedet");
                         /* 161 */     nb.put("DIALOG_TITLE", "Konfigurer");
                         /* 162 */     nb.put("CANCEL_BUTTON", "Avbryt");
                         /* 163 */     nb.put("SETUP_BUTTON", "Konfigurer");
                         /* 164 */     nb.put("DIALOG_MESSAGE_NO_CARDBOARD", "Skaff deg Cardboard-appen for å kunne konfigurere fremviseren din.");
                         /* 165 */     nb.put("DIALOG_MESSAGE_SETUP", "Konfigurer fremviseren din for en best mulig opplevelse.");
                         /* 166 */     nb.put("GO_TO_PLAYSTORE_BUTTON", "Gå til Play-butikken");
                         /* 167 */     LANGUAGE_MAP.put("nb", nb);
                         /*     */
                         /* 169 */     Map<String, String> tr = new HashMap();
                         /* 170 */     tr.put("NO_BROWSER_TEXT", "Web sitesini açacak bir tarayıcı yok");
                         /* 171 */     tr.put("DIALOG_TITLE", "Yapılandırma");
                         /* 172 */     tr.put("CANCEL_BUTTON", "İptal");
                         /* 173 */     tr.put("SETUP_BUTTON", "Kurulum");
                         /* 174 */     tr.put("DIALOG_MESSAGE_NO_CARDBOARD", "Gözlüğünüzü yapılandırmak için Cardboard uygulamasını indirin.");
                         /* 175 */     tr.put("DIALOG_MESSAGE_SETUP", "Gözlüğünüzü en iyi deneyim için hazırlayın.");
                         /* 176 */     tr.put("GO_TO_PLAYSTORE_BUTTON", "Google Play Store'a git");
                         /* 177 */     LANGUAGE_MAP.put("tr", tr);
                         /*     */
                         /* 179 */     Map<String, String> en_AU = new HashMap();
                         /* 180 */     en_AU.put("NO_BROWSER_TEXT", "No browser to open website");
                         /* 181 */     en_AU.put("DIALOG_TITLE", "Configure");
                         /* 182 */     en_AU.put("CANCEL_BUTTON", "Cancel");
                         /* 183 */     en_AU.put("SETUP_BUTTON", "Setup");
                         /* 184 */     en_AU.put("DIALOG_MESSAGE_NO_CARDBOARD", "Get the Cardboard app in order to configure your viewer.");
                         /* 185 */     en_AU.put("DIALOG_MESSAGE_SETUP", "Set up your viewer for the best experience.");
                         /* 186 */     en_AU.put("GO_TO_PLAYSTORE_BUTTON", "Go to Play Store");
                         /* 187 */     LANGUAGE_MAP.put("en_AU", en_AU);
                         /*     */
                         /* 189 */     Map<String, String> lv = new HashMap();
                         /* 190 */     lv.put("NO_BROWSER_TEXT", "Nav pārlūkprogrammas, lai atvērtu vietni");
                         /* 191 */     lv.put("DIALOG_TITLE", "Konfigurēšana");
                         /* 192 */     lv.put("CANCEL_BUTTON", "Atcelt");
                         /* 193 */     lv.put("SETUP_BUTTON", "Iestatīšana");
                         /* 194 */     lv.put("DIALOG_MESSAGE_NO_CARDBOARD", "Lai konfigurētu savu skatītāju, iegūstiet lietotni Cardboard.");
                         /* 195 */     lv.put("DIALOG_MESSAGE_SETUP", "Vislabākajiem rezultātiem iestatiet skatītāju.");
                         /* 196 */     lv.put("GO_TO_PLAYSTORE_BUTTON", "Pāriet uz Play veikalu");
                         /* 197 */     LANGUAGE_MAP.put("lv", lv);
                         /*     */
                         /* 199 */     Map<String, String> lt = new HashMap();
                         /* 200 */     lt.put("NO_BROWSER_TEXT", "Nėra naršyklės svetainei atidaryti");
                         /* 201 */     lt.put("DIALOG_TITLE", "Konfigūruoti");
                         /* 202 */     lt.put("CANCEL_BUTTON", "Atšaukti");
                         /* 203 */     lt.put("SETUP_BUTTON", "Sąranka");
                         /* 204 */     lt.put("DIALOG_MESSAGE_NO_CARDBOARD", "Gaukite „Cardboard“ programą, kad galėtumėte konfigūruoti žiūryklę.");
                         /* 205 */     lt.put("DIALOG_MESSAGE_SETUP", "Nustatykite žiūryklę, kad būtų teikiamos geriausios funkcijos.");
                         /* 206 */     lt.put("GO_TO_PLAYSTORE_BUTTON", "Eiti į „Google Play“ parduot.");
                         /* 207 */     LANGUAGE_MAP.put("lt", lt);
                         /*     */
                         /* 209 */     Map<String, String> th = new HashMap();
                         /* 210 */     th.put("NO_BROWSER_TEXT", "ไม่มีเบราว์เซอร์ที่จะใช้เปิดเว็บไซต์");
                         /* 211 */     th.put("DIALOG_TITLE", "กำหนดค่า");
                         /* 212 */     th.put("CANCEL_BUTTON", "ยกเลิก");
                         /* 213 */     th.put("SETUP_BUTTON", "ตั้งค่า");
                         /* 214 */     th.put("DIALOG_MESSAGE_NO_CARDBOARD", "ดาวน์โหลดแอป Cardboard เพื่อกำหนดค่ากล่อง");
                         /* 215 */     th.put("DIALOG_MESSAGE_SETUP", "ตั้งค่ากล่องเพื่อรับประสบการณ์ที่ดีที่สุด");
                         /* 216 */     th.put("GO_TO_PLAYSTORE_BUTTON", "ไปที่ Play สโตร์");
                         /* 217 */     LANGUAGE_MAP.put("th", th);
                         /*     */
                         /* 219 */     Map<String, String> ro = new HashMap();
                         /* 220 */     ro.put("NO_BROWSER_TEXT", "Niciun browser pentru a deschide site-ul");
                         /* 221 */     ro.put("DIALOG_TITLE", "Configurați");
                         /* 222 */     ro.put("CANCEL_BUTTON", "Anulați");
                         /* 223 */     ro.put("SETUP_BUTTON", "Configurați");
                         /* 224 */     ro.put("DIALOG_MESSAGE_NO_CARDBOARD", "Descărcați aplicația Cardboard pentru a vă configura vizualizatorul.");
                         /* 225 */     ro.put("DIALOG_MESSAGE_SETUP", "Configurați-vă vizualizatorul pentru o utilizare optimă.");
                         /* 226 */     ro.put("GO_TO_PLAYSTORE_BUTTON", "Accesați Magazin Play");
                         /* 227 */     LANGUAGE_MAP.put("ro", ro);
                         /*     */
                         /* 229 */     Map<String, String> ar = new HashMap();
                         /* 230 */     ar.put("NO_BROWSER_TEXT", "لا يتوفَّر متصفِّح لفتح الموقع الإلكتروني");
                         /* 231 */     ar.put("DIALOG_TITLE", "التهيئة");
                         /* 232 */     ar.put("CANCEL_BUTTON", "إلغاء");
                         /* 233 */     ar.put("SETUP_BUTTON", "الإعداد");
                         /* 234 */     ar.put("DIALOG_MESSAGE_NO_CARDBOARD", "‏الحصول على تطبيق Cardboard لتهيئة العارض.");
                         /* 235 */     ar.put("DIALOG_MESSAGE_SETUP", "إعداد العارض للتمتّع بأفضل تجربة.");
                         /* 236 */     ar.put("GO_TO_PLAYSTORE_BUTTON", "‏الانتقال إلى متجر Play");
                         /* 237 */     LANGUAGE_MAP.put("ar", ar);
                         /*     */
                         /* 239 */     Map<String, String> ca = new HashMap();
                         /* 240 */     ca.put("NO_BROWSER_TEXT", "No hi ha cap navegador per obrir el lloc web");
                         /* 241 */     ca.put("DIALOG_TITLE", "Configura");
                         /* 242 */     ca.put("CANCEL_BUTTON", "Cancel·la");
                         /* 243 */     ca.put("SETUP_BUTTON", "Configura");
                         /* 244 */     ca.put("DIALOG_MESSAGE_NO_CARDBOARD", "Per poder configurar el visor, baixa l'aplicació Cardboard.");
                         /* 245 */     ca.put("DIALOG_MESSAGE_SETUP", "Configura el visor per treure el màxim profit de Cardboard.");
                         /* 246 */     ca.put("GO_TO_PLAYSTORE_BUTTON", "Vés a Play Store");
                         /* 247 */     LANGUAGE_MAP.put("ca", ca);
                         /*     */
                         /* 249 */     Map<String, String> pl = new HashMap();
                         /* 250 */     pl.put("NO_BROWSER_TEXT", "Brak przeglądarki, w której można otworzyć witrynę");
                         /* 251 */     pl.put("DIALOG_TITLE", "Konfiguruj");
                         /* 252 */     pl.put("CANCEL_BUTTON", "Anuluj");
                         /* 253 */     pl.put("SETUP_BUTTON", "Konfiguracja");
                         /* 254 */     pl.put("DIALOG_MESSAGE_NO_CARDBOARD", "Pobierz aplikację Cardboard, aby ustawić gogle.");
                         /* 255 */     pl.put("DIALOG_MESSAGE_SETUP", "Ustaw gogle, aby uzyskać optymalny efekt.");
                         /* 256 */     pl.put("GO_TO_PLAYSTORE_BUTTON", "Otwórz Sklep Play");
                         /* 257 */     LANGUAGE_MAP.put("pl", pl);
                         /*     */
                         /* 259 */     Map<String, String> fr = new HashMap();
                         /* 260 */     fr.put("NO_BROWSER_TEXT", "Pas de navigateur pour ouvrir le site Web.");
                         /* 261 */     fr.put("DIALOG_TITLE", "Configurer");
                         /* 262 */     fr.put("CANCEL_BUTTON", "Annuler");
                         /* 263 */     fr.put("SETUP_BUTTON", "Configurer");
                         /* 264 */     fr.put("DIALOG_MESSAGE_NO_CARDBOARD", "Téléchargez l'application Cardboard afin de configurer votre visionneuse.");
                         /* 265 */     fr.put("DIALOG_MESSAGE_SETUP", "Pour profiter pleinement de l'application, configurez votre visionneuse.");
                         /* 266 */     fr.put("GO_TO_PLAYSTORE_BUTTON", "Accéder à Play Store");
                         /* 267 */     LANGUAGE_MAP.put("fr", fr);
                         /*     */
                         /* 269 */     Map<String, String> zh_HK = new HashMap();
                         /* 270 */     zh_HK.put("NO_BROWSER_TEXT", "沒有可開啟網站的瀏覽器");
                         /* 271 */     zh_HK.put("DIALOG_TITLE", "設定");
                         /* 272 */     zh_HK.put("CANCEL_BUTTON", "取消");
                         /* 273 */     zh_HK.put("SETUP_BUTTON", "設定");
                         /* 274 */     zh_HK.put("DIALOG_MESSAGE_NO_CARDBOARD", "取得 Cardboard 應用程式，以便設定您的檢視器。");
                         /* 275 */     zh_HK.put("DIALOG_MESSAGE_SETUP", "調整您的檢視器設定以取得最佳使用體驗。");
                         /* 276 */     zh_HK.put("GO_TO_PLAYSTORE_BUTTON", "前往 Play 商店");
                         /* 277 */     LANGUAGE_MAP.put("zh_HK", zh_HK);
                         /*     */
                         /* 279 */     Map<String, String> pt_BR = new HashMap();
                         /* 280 */     pt_BR.put("NO_BROWSER_TEXT", "Nenhum navegador encontrado para abrir o site");
                         /* 281 */     pt_BR.put("DIALOG_TITLE", "Configurar");
                         /* 282 */     pt_BR.put("CANCEL_BUTTON", "Cancelar");
                         /* 283 */     pt_BR.put("SETUP_BUTTON", "Configurar");
                         /* 284 */     pt_BR.put("DIALOG_MESSAGE_NO_CARDBOARD", "Faça o download do app do Google Cardboard para configurar seu visualizador.");
                         /* 285 */     pt_BR.put("DIALOG_MESSAGE_SETUP", "Configure seu visualizador para ter a melhor experiência.");
                         /* 286 */     pt_BR.put("GO_TO_PLAYSTORE_BUTTON", "Acessar a Google Play Store");
                         /* 287 */     LANGUAGE_MAP.put("pt_BR", pt_BR);
                         /*     */
                         /* 289 */     Map<String, String> hr = new HashMap();
                         /* 290 */     hr.put("NO_BROWSER_TEXT", "Nema preglednika za otvaranje web-lokacije");
                         /* 291 */     hr.put("DIALOG_TITLE", "Konfiguriraj");
                         /* 292 */     hr.put("CANCEL_BUTTON", "Odustani");
                         /* 293 */     hr.put("SETUP_BUTTON", "Postavljanje");
                         /* 294 */     hr.put("DIALOG_MESSAGE_NO_CARDBOARD", "Nabavite aplikaciju Cardboard da biste konfigurirali masku za virtualnu stvarnost.");
                         /* 295 */     hr.put("DIALOG_MESSAGE_SETUP", "Postavite masku za virtualnu stvarnost da biste u potpunosti iskoristili sve značajke.");
                         /* 296 */     hr.put("GO_TO_PLAYSTORE_BUTTON", "Idi na Trgovinu Play");
                         /* 297 */     LANGUAGE_MAP.put("hr", hr);
                         /*     */
                         /* 299 */     Map<String, String> es_US = new HashMap();
                         /* 300 */     es_US.put("NO_BROWSER_TEXT", "No hay ningún navegador para abrir el sitio web");
                         /* 301 */     es_US.put("DIALOG_TITLE", "Configurar");
                         /* 302 */     es_US.put("CANCEL_BUTTON", "Cancelar");
                         /* 303 */     es_US.put("SETUP_BUTTON", "Configuración");
                         /* 304 */     es_US.put("DIALOG_MESSAGE_NO_CARDBOARD", "Obtén la aplicación Cardboard para poder configurar el visor.");
                         /* 305 */     es_US.put("DIALOG_MESSAGE_SETUP", "Configura el visor para obtener la mejor experiencia.");
                         /* 306 */     es_US.put("GO_TO_PLAYSTORE_BUTTON", "Ir a Play Store");
                         /* 307 */     LANGUAGE_MAP.put("es_US", es_US);
                         /*     */
                         /* 309 */     Map<String, String> zh_TW = new HashMap();
                         /* 310 */     zh_TW.put("NO_BROWSER_TEXT", "沒有可開啟網站的瀏覽器");
                         /* 311 */     zh_TW.put("DIALOG_TITLE", "設定");
                         /* 312 */     zh_TW.put("CANCEL_BUTTON", "取消");
                         /* 313 */     zh_TW.put("SETUP_BUTTON", "設定");
                         /* 314 */     zh_TW.put("DIALOG_MESSAGE_NO_CARDBOARD", "取得 Cardboard 應用程式，以便設定您的檢視器。");
                         /* 315 */     zh_TW.put("DIALOG_MESSAGE_SETUP", "調整您的檢視器設定以取得最佳使用體驗。");
                         /* 316 */     zh_TW.put("GO_TO_PLAYSTORE_BUTTON", "前往 Play 商店");
                         /* 317 */     LANGUAGE_MAP.put("zh_TW", zh_TW);
                         /*     */
                         /* 319 */     Map<String, String> hu = new HashMap();
                         /* 320 */     hu.put("NO_BROWSER_TEXT", "Nem található böngésző a webhely megnyitásához");
                         /* 321 */     hu.put("DIALOG_TITLE", "Konfigurálás");
                         /* 322 */     hu.put("CANCEL_BUTTON", "Mégse");
                         /* 323 */     hu.put("SETUP_BUTTON", "Beállítás");
                         /* 324 */     hu.put("DIALOG_MESSAGE_NO_CARDBOARD", "Szemüvege konfigurálásához töltse le a Cardboard alkalmazást.");
                         /* 325 */     hu.put("DIALOG_MESSAGE_SETUP", "A szemüveg beállítása a legjobb élmény eléréséhez.");
                         /* 326 */     hu.put("GO_TO_PLAYSTORE_BUTTON", "Play Áruház megnyitása");
                         /* 327 */     LANGUAGE_MAP.put("hu", hu);
                         /*     */
                         /* 329 */     Map<String, String> fa = new HashMap();
                         /* 330 */     fa.put("NO_BROWSER_TEXT", "مرورگری برای باز کردن وب‌سایت نیست");
                         /* 331 */     fa.put("DIALOG_TITLE", "پیکربندی");
                         /* 332 */     fa.put("CANCEL_BUTTON", "لغو");
                         /* 333 */     fa.put("SETUP_BUTTON", "راه‌اندازی");
                         /* 334 */     fa.put("DIALOG_MESSAGE_NO_CARDBOARD", "‏برای پیکربندی نظاره‌گر، برنامه Cardboard را دریافت کنید.");
                         /* 335 */     fa.put("DIALOG_MESSAGE_SETUP", "برای اینکه بهترین تجربه را داشته باشید، نظاره‌گرتان را راه‌اندازی کنید.");
                         /* 336 */     fa.put("GO_TO_PLAYSTORE_BUTTON", "‏برو به فروشگاه Play");
                         /* 337 */     LANGUAGE_MAP.put("fa", fa);
                         /*     */
                         /* 339 */     Map<String, String> hi = new HashMap();
                         /* 340 */     hi.put("NO_BROWSER_TEXT", "वेबसाइट खोलने के लिए कोई ब्राउज़र नहीं");
                         /* 341 */     hi.put("DIALOG_TITLE", "कॉन्फ़िगर करें");
                         /* 342 */     hi.put("CANCEL_BUTTON", "रोकें");
                         /* 343 */     hi.put("SETUP_BUTTON", "सेटअप");
                         /* 344 */     hi.put("DIALOG_MESSAGE_NO_CARDBOARD", "अपना व्यूअर कॉन्फ़िगर करने के लिए कार्डबोर्ड ऐप्लिकेशन प्राप्त करें.");
                         /* 345 */     hi.put("DIALOG_MESSAGE_SETUP", "श्रेष्ठ अनुभव के लिए अपना व्यूअर सेट करें.");
                         /* 346 */     hi.put("GO_TO_PLAYSTORE_BUTTON", "Play स्‍टोर पर जाएं");
                         /* 347 */     LANGUAGE_MAP.put("hi", hi);
                         /*     */
                         /* 349 */     Map<String, String> fi = new HashMap();
                         /* 350 */     fi.put("NO_BROWSER_TEXT", "Ei verkkosivuston avaamiseen sopivaa selainta");
                         /* 351 */     fi.put("DIALOG_TITLE", "Määritä");
                         /* 352 */     fi.put("CANCEL_BUTTON", "Peruuta");
                         /* 353 */     fi.put("SETUP_BUTTON", "Aloita määritys");
                         /* 354 */     fi.put("DIALOG_MESSAGE_NO_CARDBOARD", "Lataa Cardboard-sovellus lasien asetusten määrittämistä varten.");
                         /* 355 */     fi.put("DIALOG_MESSAGE_SETUP", "Määritä lasien asetukset, jotta katselukokemus on mahdollisimman miellyttävä.");
                         /* 356 */     fi.put("GO_TO_PLAYSTORE_BUTTON", "Siirry Play Kauppaan");
                         /* 357 */     LANGUAGE_MAP.put("fi", fi);
                         /*     */
                         /* 359 */     Map<String, String> da = new HashMap();
                         /* 360 */     da.put("NO_BROWSER_TEXT", "Der er ingen browser til at åbne websitet");
                         /* 361 */     da.put("DIALOG_TITLE", "Konfigurer");
                         /* 362 */     da.put("CANCEL_BUTTON", "Annuller");
                         /* 363 */     da.put("SETUP_BUTTON", "Konfigurer");
                         /* 364 */     da.put("DIALOG_MESSAGE_NO_CARDBOARD", "Få Cardboard-appen, så du kan konfigurere fremviseren.");
                         /* 365 */     da.put("DIALOG_MESSAGE_SETUP", "Konfigurer din fremviser for at få den bedste oplevelse.");
                         /* 366 */     da.put("GO_TO_PLAYSTORE_BUTTON", "Gå til Play Butik");
                         /* 367 */     LANGUAGE_MAP.put("da", da);
                         /*     */
                         /* 369 */     Map<String, String> en_IN = new HashMap();
                         /* 370 */     en_IN.put("NO_BROWSER_TEXT", "No browser to open website");
                         /* 371 */     en_IN.put("DIALOG_TITLE", "Configure");
                         /* 372 */     en_IN.put("CANCEL_BUTTON", "Cancel");
                         /* 373 */     en_IN.put("SETUP_BUTTON", "Setup");
                         /* 374 */     en_IN.put("DIALOG_MESSAGE_NO_CARDBOARD", "Get the Cardboard app in order to configure your viewer.");
                         /* 375 */     en_IN.put("DIALOG_MESSAGE_SETUP", "Set up your viewer for the best experience.");
                         /* 376 */     en_IN.put("GO_TO_PLAYSTORE_BUTTON", "Go to Play Store");
                         /* 377 */     LANGUAGE_MAP.put("en_IN", en_IN);
                         /*     */
                         /* 379 */     Map<String, String> ja = new HashMap();
                         /* 380 */     ja.put("NO_BROWSER_TEXT", "ウェブサイトを開くブラウザがありません");
                         /* 381 */     ja.put("DIALOG_TITLE", "設定");
                         /* 382 */     ja.put("CANCEL_BUTTON", "キャンセル");
                         /* 383 */     ja.put("SETUP_BUTTON", "セットアップ");
                         /* 384 */     ja.put("DIALOG_MESSAGE_NO_CARDBOARD", "ビューアを設定するには、Cardboardアプリを入手してください。");
                         /* 385 */     ja.put("DIALOG_MESSAGE_SETUP", "快適にご利用いただくために、ビューアをセットアップしてください。");
                         /* 386 */     ja.put("GO_TO_PLAYSTORE_BUTTON", "Google Playストアへ");
                         /* 387 */     LANGUAGE_MAP.put("ja", ja);
                         /*     */
                         /* 389 */     Map<String, String> he = new HashMap();
                         /* 390 */     he.put("NO_BROWSER_TEXT", "אין דפדפן שיכול לפתוח את האתר");
                         /* 391 */     he.put("DIALOG_TITLE", "הגדרה");
                         /* 392 */     he.put("CANCEL_BUTTON", "ביטול");
                         /* 393 */     he.put("SETUP_BUTTON", "הגדר");
                         /* 394 */     he.put("DIALOG_MESSAGE_NO_CARDBOARD", "‏הורד את אפליקציית Cardboard כדי להגדיר את המציג.");
                         /* 395 */     he.put("DIALOG_MESSAGE_SETUP", "הגדר את המציג לקבלת החוויה הטובה ביותר.");
                         /* 396 */     he.put("GO_TO_PLAYSTORE_BUTTON", "‏עבור לחנות Play");
                         /* 397 */     LANGUAGE_MAP.put("he", he);
                         /*     */
                         /* 399 */     Map<String, String> zh_CN = new HashMap();
                         /* 400 */     zh_CN.put("NO_BROWSER_TEXT", "找不到可以打开网站的浏览器");
                         /* 401 */     zh_CN.put("DIALOG_TITLE", "配置");
                         /* 402 */     zh_CN.put("CANCEL_BUTTON", "取消");
                         /* 403 */     zh_CN.put("SETUP_BUTTON", "设置");
                         /* 404 */     zh_CN.put("DIALOG_MESSAGE_NO_CARDBOARD", "获取 Cardboard 应用以配置您的眼镜。");
                         /* 405 */     zh_CN.put("DIALOG_MESSAGE_SETUP", "设置眼镜以获得最佳体验。");
                         /* 406 */     zh_CN.put("GO_TO_PLAYSTORE_BUTTON", "转到 Play 商店");
                         /* 407 */     LANGUAGE_MAP.put("zh_CN", zh_CN);
                         /*     */
                         /* 409 */     Map<String, String> sr = new HashMap();
                         /* 410 */     sr.put("NO_BROWSER_TEXT", "Нема прегледача за отварање веб-сајта");
                         /* 411 */     sr.put("DIALOG_TITLE", "Конфигуришите");
                         /* 412 */     sr.put("CANCEL_BUTTON", "Откажи");
                         /* 413 */     sr.put("SETUP_BUTTON", "Подешавање");
                         /* 414 */     sr.put("DIALOG_MESSAGE_NO_CARDBOARD", "Преузмите апликацију Cardboard да бисте конфигурисали маску.");
                         /* 415 */     sr.put("DIALOG_MESSAGE_SETUP", "Подесите маску за најбољи доживљај.");
                         /* 416 */     sr.put("GO_TO_PLAYSTORE_BUTTON", "Иди у Play продавницу");
                         /* 417 */     LANGUAGE_MAP.put("sr", sr);
                         /*     */
                         /* 419 */     Map<String, String> ko = new HashMap();
                         /* 420 */     ko.put("NO_BROWSER_TEXT", "웹사이트를 열 브라우저가 없습니다.");
                         /* 421 */     ko.put("DIALOG_TITLE", "설정");
                         /* 422 */     ko.put("CANCEL_BUTTON", "취소");
                         /* 423 */     ko.put("SETUP_BUTTON", "설정");
                         /* 424 */     ko.put("DIALOG_MESSAGE_NO_CARDBOARD", "뷰어를 설정하려면 Cardboard 앱을 받으세요.");
                         /* 425 */     ko.put("DIALOG_MESSAGE_SETUP", "최적의 경험을 위해 뷰어를 설정하세요.");
                         /* 426 */     ko.put("GO_TO_PLAYSTORE_BUTTON", "Play 스토어로 이동");
                         /* 427 */     LANGUAGE_MAP.put("ko", ko);
                         /*     */
                         /* 429 */     Map<String, String> sv = new HashMap();
                         /* 430 */     sv.put("NO_BROWSER_TEXT", "Ingen webbläsare kan öppna webbsidan");
                         /* 431 */     sv.put("DIALOG_TITLE", "Konfigurera");
                         /* 432 */     sv.put("CANCEL_BUTTON", "Avbryt");
                         /* 433 */     sv.put("SETUP_BUTTON", "Konfiguration");
                         /* 434 */     sv.put("DIALOG_MESSAGE_NO_CARDBOARD", "Ladda ned Cardboard-appen om du vill konfigurera visaren.");
                         /* 435 */     sv.put("DIALOG_MESSAGE_SETUP", "Konfigurera visaren för bästa upplevelse.");
                         /* 436 */     sv.put("GO_TO_PLAYSTORE_BUTTON", "Öppna Play Butik");
                         /* 437 */     LANGUAGE_MAP.put("sv", sv);
                         /*     */
                         /* 439 */     Map<String, String> sk = new HashMap();
                         /* 440 */     sk.put("NO_BROWSER_TEXT", "Žiaden prehliadač na otvorenie webu");
                         /* 441 */     sk.put("DIALOG_TITLE", "Konfigurácia");
                         /* 442 */     sk.put("CANCEL_BUTTON", "Zrušiť");
                         /* 443 */     sk.put("SETUP_BUTTON", "Nastaviť");
                         /* 444 */     sk.put("DIALOG_MESSAGE_NO_CARDBOARD", "Získajte aplikáciu Cardboard, aby ste mohli konfigurovať zobrazovač.");
                         /* 445 */     sk.put("DIALOG_MESSAGE_SETUP", "Nastavte si zobrazovač a dosiahnite tie najlepšie výsledky.");
                         /* 446 */     sk.put("GO_TO_PLAYSTORE_BUTTON", "Prejsť do služby Obchod Play");
                         /* 447 */     LANGUAGE_MAP.put("sk", sk);
                         /*     */
                         /* 449 */     Map<String, String> de = new HashMap();
                         /* 450 */     de.put("NO_BROWSER_TEXT", "Kein Browser zum Öffnen der Website gefunden");
                         /* 451 */     de.put("DIALOG_TITLE", "Konfigurieren");
                         /* 452 */     de.put("CANCEL_BUTTON", "Abbrechen");
                         /* 453 */     de.put("SETUP_BUTTON", "Einrichtung");
                         /* 454 */     de.put("DIALOG_MESSAGE_NO_CARDBOARD", "Holen Sie sich die Cardboard App zum Konfigurieren Ihrer VR-Brille.");
                         /* 455 */     de.put("DIALOG_MESSAGE_SETUP", "Richten Sie Ihre VR-Brille für optimale Virtual-Reality-Erlebnisse ein.");
                         /* 456 */     de.put("GO_TO_PLAYSTORE_BUTTON", "Zum Play Store");
                         /* 457 */     LANGUAGE_MAP.put("de", de);
                         /*     */
                         /* 459 */     Map<String, String> en_GB = new HashMap();
                         /* 460 */     en_GB.put("NO_BROWSER_TEXT", "No browser to open website");
                         /* 461 */     en_GB.put("DIALOG_TITLE", "Configure");
                         /* 462 */     en_GB.put("CANCEL_BUTTON", "Cancel");
                         /* 463 */     en_GB.put("SETUP_BUTTON", "Setup");
                         /* 464 */     en_GB.put("DIALOG_MESSAGE_NO_CARDBOARD", "Get the Cardboard app in order to configure your viewer.");
                         /* 465 */     en_GB.put("DIALOG_MESSAGE_SETUP", "Set up your viewer for the best experience.");
                         /* 466 */     en_GB.put("GO_TO_PLAYSTORE_BUTTON", "Go to Play Store");
                         /* 467 */     LANGUAGE_MAP.put("en_GB", en_GB);
                         /*     */
                         /* 469 */     Map<String, String> fil = new HashMap();
                         /* 470 */     fil.put("NO_BROWSER_TEXT", "Walang browser upang buksan ang website");
                         /* 471 */     fil.put("DIALOG_TITLE", "I-configure");
                         /* 472 */     fil.put("CANCEL_BUTTON", "Kanselahin");
                         /* 473 */     fil.put("SETUP_BUTTON", "I-setup");
                         /* 474 */     fil.put("DIALOG_MESSAGE_NO_CARDBOARD", "Kunin ang Cardboard app upang i-configure ang iyong viewer.");
                         /* 475 */     fil.put("DIALOG_MESSAGE_SETUP", "I-set up ang iyong viewer para sa pinakamagandang karanasan.");
                         /* 476 */     fil.put("GO_TO_PLAYSTORE_BUTTON", "Pumunta sa Play Store");
                         /* 477 */     LANGUAGE_MAP.put("fil", fil);
                         /*     */
                         /* 479 */     Map<String, String> uk = new HashMap();
                         /* 480 */     uk.put("NO_BROWSER_TEXT", "Немає веб-переглядача для сайту");
                         /* 481 */     uk.put("DIALOG_TITLE", "Налаштувати");
                         /* 482 */     uk.put("CANCEL_BUTTON", "Скасувати");
                         /* 483 */     uk.put("SETUP_BUTTON", "Налаштування");
                         /* 484 */     uk.put("DIALOG_MESSAGE_NO_CARDBOARD", "Завантажте додаток Cardboard, щоб налаштувати окуляри.");
                         /* 485 */     uk.put("DIALOG_MESSAGE_SETUP", "Налаштуйте окуляри й отримайте найкращі враження.");
                         /* 486 */     uk.put("GO_TO_PLAYSTORE_BUTTON", "Перейти в Google Play");
                         /* 487 */     LANGUAGE_MAP.put("uk", uk);
                         /*     */
                         /* 489 */     Map<String, String> sl = new HashMap();
                         /* 490 */     sl.put("NO_BROWSER_TEXT", "Tega mesta ni mogoče odpreti z nobenim brskalnikom");
                         /* 491 */     sl.put("DIALOG_TITLE", "Konfiguriranje");
                         /* 492 */     sl.put("CANCEL_BUTTON", "Prekliči");
                         /* 493 */     sl.put("SETUP_BUTTON", "Namesti");
                         /* 494 */     sl.put("DIALOG_MESSAGE_NO_CARDBOARD", "Če želite konfigurirati pregledovalnik, namestite aplikacijo Cardboard.");
                         /* 495 */     sl.put("DIALOG_MESSAGE_SETUP", "Da bo izkušnja čim boljša, namestite pregledovalnik.");
                         /* 496 */     sl.put("GO_TO_PLAYSTORE_BUTTON", "V Google Play");
                         /* 497 */     LANGUAGE_MAP.put("sl", sl);
                         /*     */   }
    /*     */
    /*     */   private static Map<String, String> getLanguageMap(Locale locale)
    /*     */
    {
        /* 502 */     String language = locale.getLanguage();

        /* 503 */     if (LANGUAGE_MAP.containsKey(language)) {
            /* 504 */       return (Map)LANGUAGE_MAP.get(language);
            /*     */
        }

        /*     */
        /* 507 */     return (Map)LANGUAGE_MAP.get("en");
        /*     */
    }
    /*     */
    /*     */   private static String getString(String stringId, Locale locale)
    /*     */
    {
        /* 512 */     Map<String, String> stringMap = getLanguageMap(locale);

        /* 513 */     if (stringMap.containsKey(stringId)) {
            /* 514 */       return (String)stringMap.get(stringId);
            /*     */
        }

        /*     */
        /* 517 */     return (String)((Map)LANGUAGE_MAP.get("en")).get(stringId);
        /*     */
    }
    /*     */
    /*     */   public static String getString(String stringId)
    /*     */
    {
        /* 522 */     return getString(stringId, Locale.getDefault());
        /*     */
    }
    /*     */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/Strings.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */