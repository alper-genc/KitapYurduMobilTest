Feature: İlgi Görenler
  Kitapyurdu uygulamasında İlgi Görenler bölümünün işlevselliğini test etme

  Background: Uygulama açılır ve ana sayfa yüklenir

  @ilgi_gorenler @anasayfa
  Scenario: İlgi Görenler bölümündeki Tümü butonuna tıklama
    When İlgi Görenler Tümü butonuna tıkla
    Then İlgi Görenler sayfası açılmalıdır

