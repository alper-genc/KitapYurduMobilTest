Feature: Çok Satanlar
  Kitapyurdu uygulamasında Çok Satanlar bölümünün işlevselliğini test etme

  Background: Uygulama açılır ve ana sayfa yüklenir

  @cok_satanlar @anasayfa
  Scenario: Çok Satanlar bölümündeki Tümü butonuna tıklama
    When Çok Satanlar Tümü butonuna tıkla
    Then Çok Satanlar sayfası açılmalıdır

