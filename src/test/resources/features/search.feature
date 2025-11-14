Feature: Kitap Arama Senaryosu
  Mobil uygulamada arama fonksiyonunun doğru çalıştığını doğrulayan senaryolar.

  Background: Uygulama açılır ve temel bileşenler hazır hale getirilir

  @arama @kitap
  Scenario: Kullanıcı kitap araması yapar
    Given Ara sekmesine tıkla
    When Arama kutusuna "Suç ve Ceza" yaz
    Then Arama sonuçları listelenmelidir
    And Arama sonucuna "Suç ve Ceza" tıkla
