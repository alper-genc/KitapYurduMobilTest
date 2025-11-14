# 📱 Kitapyurdu Mobil Test Otomasyonu

Kitapyurdu mobil uygulamasındaki kritik müşteri akışlarını Cucumber 7 + Appium 8 + TestNG 7 kombinasyonu ile doğrulayan Testinium tabanlı otomasyon projesidir. Android gerçek cihazlarda ve emülatörlerde native testler koşar, başarısız adımlarda otomatik ekran görüntüsü alır ve sonuçları Allure, Cucumber HTML/JSON/JUnit ile Excel formatlarında raporlar.

## 🚀 Öne Çıkanlar
- Cucumber BDD (Gherkin) + TestNG entegrasyonu sayesinde okunabilir senaryolar ve CI dostu koşular
- Appium 8 (UiAutomator2) ile gerçek cihaz/emülatör desteği; W3C Actions API ile gelişmiş jestler
- Testinium Page Object Model ve TestiniumButton/TestiniumTextBox gibi özel element sarmalayıcıları ile tekrar kullanılabilir ekran etkileşimleri
- Driver lifecycle hook'ları (@BeforeAll/@After) ile tek seferlik sürücü kurulumu, senaryo bağlamı ve otomatik ekran görüntüsü yönetimi
- Çok formatlı raporlama (Allure, Cucumber HTML/JSON/JUnit, Excel) + Allure history klasörüyle trend takibi
- Maven smoke/regression profilleri ve tag bazlı filtreleme ile seçici koşu desteği

## 🧱 Teknoloji Yığını
- Java 21 (Temurin önerilir)
- Apache Maven 3.9+
- io.cucumber:cucumber-java & cucumber-testng 7.15.0
- io.appium:java-client 8.6.0 (UiAutomator2)
- org.testng:testng 7.8.0
- org.seleniumhq.selenium:selenium-java 4.15.0
- io.qameta.allure:* 2.25.0 + allure-maven 2.12.0
- ch.qos.logback:logback-classic 1.4.14, org.assertj:assertj-core 3.24.2
- Node.js 18+ & Appium Server 2.x
- Android SDK (platform 34+) + platform-tools (adb, emulator, avdmanager)

## 📋 Gereksinimler

### Yazılım
- JDK 21 kurulu ve `JAVA_HOME`/`PATH` değişkenleri ayarlı
- Apache Maven 3.9+ ve Git
- Node.js 18+ ve npm (Appium 2.x kurulumu için)
- Appium Server 2.0+ (`npm install -g appium` ve `appium driver install uiautomator2`)
- Android SDK + platform-tools + system-images (AVD ve `adb` için, `ANDROID_HOME` tanımlı olmalı)
- Allure Command Line 2.25+ (raporları görüntülemek için önerilir)

### Cihaz/Emülatör
- USB hata ayıklaması açık Android 12+ fiziksel cihaz **veya** `Medium_Phone_API_36.1` / Pixel 6 benzeri bir AVD
- Kitapyurdu uygulaması (`com.mobisoft.kitapyurdu`) yüklü olmalı; gerekirse APK’yı `adb install` ile yükleyin
- `adb devices` çıktısında cihaz/emülatör görünür olmalı

## 🛠️ Kurulum & İlk Çalıştırma
1. **Projeyi klonlayın**
   ```bash
   git clone <repo-url>
   cd KitapYurdu
   ```
2. **Bağımlılıkları indirin**
   ```bash
   mvn clean install -DskipTests
   ```
3. **Konfigürasyonu yapın**  
   `env/default/default.properties` dosyasını güncelleyip Appium sunucusu, cihaz adı, paket ve activity bilgilerini kendi ortamınıza göre ayarlayın (gerekirse `env/qa/default.properties` gibi yeni bir klasör oluşturabilirsiniz).
4. **Appium Server’ı başlatın**
   ```bash
   npm install -g appium
   appium driver install uiautomator2
   appium --port 4723
   ```
5. **Emülatörü açın veya cihazı bağlayın**
   ```bash
   emulator -avd Medium_Phone_API_36.1
   adb devices
   adb install /path/to/kitapyurdu.apk   # uygulama yoksa
   ```
6. **Testleri çalıştırın**
   ```bash
   mvn clean test
   ```
   `JAVA_HOME`, `ANDROID_HOME` ve `PATH` değişkenlerinin terminal oturumunda erişilebilir olduğundan emin olun.

## ⚙️ Konfigürasyon
`DriverFactory` çalışma anında `env/default/default.properties` dosyasını okur. En sık kullanılan ayarlar aşağıdadır:

| Property | Açıklama | Varsayılan |
| --- | --- | --- |
| `appium.server.url` | Appium sunucusunun temel adresi | `http://127.0.0.1:4723` |
| `android.device.name` | `adb devices` çıktısındaki cihaz/AVD adı | `emulator-5554` |
| `android.avd.name` | Otomatik başlatılacak AVD adı | `Medium_Phone_API_36.1` |
| `android.automation.name` | Appium otomasyon motoru | `UiAutomator2` |
| `app.package` | Kitapyurdu Android paket adı | `com.mobisoft.kitapyurdu` |
| `app.activity` | Başlatılacak activity | `com.mobisoft.kitapyurdu.main.SplashActivity` |
| `implicit.wait` | Saniye cinsinden implicit bekleme | `10` |
| `test.timeout` | Senaryo özel bekleme limiti | `30` |
| `reports.dir` | Cucumber HTML rapor klasörü | `target/cucumber-reports` |
| `enable.multithreading` | Paralel sürücü desteği | `false` |

> Notlar:
> - `app.apk.path` alanını doldurursanız Appium cihazda uygulama yoksa APK’yı yükler.  
> - Log seviyeleri `src/test/resources/logback-test.xml` dosyasından, Allure bağlantıları `src/test/resources/allure.properties` dosyasından yönetilir.

## 🧪 Testleri Çalıştırma
- **Varsayılan koşu (TestNG + Cucumber runner)**  
  ```bash
  mvn clean test
  ```  
  `com.testinium.And.runners.CucumberTestRunner` içindeki `@CucumberOptions` değeri gereği varsayılan filtre `@arama or @smoke` tag’leridir.

- **Tag bazlı çalıştırma**  
  ```bash
  mvn test -Dcucumber.filter.tags="@kampanya"
  mvn test -Dcucumber.filter.tags="@ilgi_gorenler and not @wip"
  ```

- **Maven profilleri**  
  ```bash
  mvn clean test -Psmoke
  mvn clean test -Pregression
  ```

- **IDE’den çalıştırma**  
  `CucumberTestRunner` sınıfını IntelliJ IDEA’dan *Run* edin. Tag değiştirmek için ilgili sınıftaki `tags` alanını düzenleyin veya komut satırından `-Dcucumber.filter.tags` parametresini geçin.

- **Cucumber Publish**  
  Bulutta rapor paylaşmak için `CUCUMBER_PUBLISH_TOKEN` ortam değişkenini tanımlayabilirsiniz; devre dışı bırakmak için `-Dcucumber.publish.enabled=false` kullanın.

## 🗂️ Feature Dosyaları & Tag'ler
| Dosya | Açıklama | Başlıca tag'ler |
| --- | --- | --- |
| `search.feature` | Arama sekmesi, kitap araması ve sonuç doğrulamaları | `@arama @kitap` |
| `cok_satanlar.feature` | Ana sayfada “Çok Satanlar” Tümü butonu akışı | `@cok_satanlar @anasayfa` |
| `ilgi_gorenler.feature` | “İlgi Görenler” Tümü butonu akışı | `@ilgi_gorenler @anasayfa` |
| `kampanyalar.feature` | Kampanyalar sekmesine geçme ve öne çıkan kart seçimi | `@kampanya` |
| `login.feature` | Temel placeholder senaryosu (isteğe göre genişletilebilir) | `@tag1 @tag2` |

Yeni senaryoları `src/test/resources/features` altına ekleyip uygun tag’lerle filtreleyebilirsiniz.

## 🧬 Mimari & Paketler
```
src/test/
├── java/com/testinium/And/
│   ├── Pages/          # Page Object'lar (HomePage, CampaignsPage, SearchPage, ...)
│   ├── PageSteps/      # Cucumber step definition'ları
│   ├── PageElement/    # TestiniumButton, TestiniumTextBox, TestiniumSwipe vb. sarmalayıcılar
│   ├── Backend/        # TestiniumAutomationContext, ContextKeys, MobileAutomationException
│   ├── Util/
│   │   ├── Driver/     # Driver, DriverFactory ve lifecycle hook'ları
│   │   └── Report/     # ExcelUtil ile Excel raporu üretimi
│   └── runners/        # CucumberTestRunner (TestNG köprüsü)
└── resources/
    ├── features/       # Gherkin senaryoları
    ├── testng.xml      # Surefire giriş noktası
    ├── allure.properties
    └── logback-test.xml
```
- `DriverFactory` Appium `UiAutomator2Options` ile cihaz/emülatör ayarlarını okur.  
- `Driver` sınıfı `@BeforeAll/@After` hook’ları ile tek seferlik sürücü kurar, TestiniumAutomationContext’e senaryo bilgisi yazar ve Excel raporunu tetikler.  
- `MobileAutomationException` başarısızlık durumunda `screenshots/` klasörüne otomatik ekran görüntüsü kaydeder ve bağlam bilgilerini günceller.  
- `ExcelUtil` her koşuda `reports/excel/AndroidAutomationReport-<dd-MM-yyyy>.xlsx` dosyasını güncelleyerek test özetini saklar.

## 📊 Raporlama & Artefaktlar
- **Cucumber HTML/JSON/JUnit**: `target/cucumber-reports/` altında `cucumber-html-reports.html`, `Cucumber.json`, `Cucumber.xml`
- **Allure Sonuçları**: `target/allure-results/` (ham sonuç); statik rapor `allure-report/` klasöründe tutulur. Görüntülemek için:
  ```bash
  allure serve target/allure-results
  # veya
  mvn allure:report
  ```
- **Excel raporu**: `reports/excel/AndroidAutomationReport-<dd-MM-yyyy>.xlsx`
- **Loglar**: `logs/api.log` (Logback konfigürasyonu `logback-test.xml`)
- **Ekran görüntüleri**: `screenshots/screenshot <Scenario>.png`
- **Allure history**: `allure-report/history` klasörünü CI’de saklayarak trend grafikleri koruyabilirsiniz.

## 🛠️ Sorun Giderme
1. **Appium bağlantı hatası**
   ```bash
   curl http://127.0.0.1:4723/wd/hub/status
   ```
   Sunucu cevap vermiyorsa Appium’u yeniden başlatın veya `appium.server.url` değerini kontrol edin.
2. **Cihaz/Emülatör görünmüyor**
   ```bash
   adb kill-server
   adb start-server
   adb devices
   ```
   Gerekirse USB kablosunu çıkarıp tekrar bağlayın ya da AVD’yi yeniden başlatın.
3. **Kitapyurdu APK’sı yok**
   ```bash
   adb install /path/to/kitapyurdu.apk
   ```
   Paket adı değiştiyse `app.package`/`app.activity` alanlarını güncelleyin.
4. **UiAutomator2 başlangıç timeout’u**
   `default.properties` dosyasındaki `implicit.wait` değerini artırın veya `DriverFactory` içinde `setUiautomator2ServerLaunchTimeout` süresini yükseltin.
5. **Dinamik locator sebebiyle Kampanyalar butonu bulunamıyor**  
   `HomePage` sınıfındaki locator’ı Appium Inspector ile güncel ID/XPath bilgisiyle değiştirin.
6. **Yanlış activity yükleniyor**  
   Geçerli activity’yi kontrol edin:
   ```bash
   adb shell dumpsys window | grep mCurrentFocus
   ```
   Çıktıya göre `app.activity` değerini güncelleyin.

## 🤝 Katkıda Bulunma
1. Reponun fork’unu alın
2. Feature branch açın: `git checkout -b feature/yeni-ozellik`
3. Kodunuzu ve testlerinizi çalıştırın: `mvn clean test`
4. Değişiklikleri commit edin: `git commit -am "Özellik: <açıklama>"`
5. Branch’i push edin ve Pull Request açın

## ℹ️ Meta
- ArtifactId: `kitapyurdu-mobile-automation`
- Test Runner: `com.testinium.And.runners.CucumberTestRunner`
- Varsayılan tag filtresi: `@arama or @smoke`
- Excel raporu: `reports/excel/AndroidAutomationReport-<dd-MM-yyyy>.xlsx`
- Geliştirici: Testinium QA Ekibi
- Son Güncelleme: Kasım 2025
