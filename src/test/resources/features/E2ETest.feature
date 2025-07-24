Feature: Ürün filtreleme ve sepete ekleme işlemi

  Background:
    Given kullanici uygulamaya girer
    And "hediye & ekstra" islemini secer
    When alt menuden "hesabım" iconuna tiklar
    And "Üye Girişi" butonuna tiklar
    When test datadaki 1.sheetteki "gecerli" mail ve "gecerli" sifre ile hesaba giris yapilir

  Scenario: Filtreleme ile ürün seçimi yapılmalı, sepete eklenmeli ve ürün bilgileri doğrulanmalıdır
    When alt menuden "kategoriler" iconuna tiklar
    And sayfadaki "Doğum Günü" sekmesinden, "Kadına Hediye" sayfasina gider
    When acilan Siparis Nereye Gonderilecek alanindaki adres kismi "Karşıyaka" olarak doldurulur
    And kullanici sayfanin ustunde bulunan "filtrele" butonuna tiklar

    When filtreleme sayfasindaki "kategori" butonuna tiklar ve secim yapar
      |kolye ucu  |
      |altın Kolye|
    Then filtreme sayfasindaki "kategori" yazisinin yanindaki secim sayisi dogrulanir

    When filtreleme sayfasindaki "Ne İçin" butonuna tiklar ve secim yapar
      |doğum Günü |
    Then filtreme sayfasindaki "Ne İçin" yazisinin yanindaki secim sayisi dogrulanir

    When filtreleme sayfasindaki "Kime" butonuna tiklar ve secim yapar
      |sevgiliye  |
    Then filtreme sayfasindaki "Kime" yazisinin yanindaki secim sayisi dogrulanir

    When filtreleme sayfasindaki "Renk" butonuna tiklar ve secim yapar
      |mavi       |
      |beyaz      |
    Then filtreme sayfasindaki "Renk" yazisinin yanindaki secim sayisi dogrulanir

    When filtreleme sayfasindaki "Marka" butonuna tiklar ve secim yapar
      |engin gold |
      |glorria    |
    Then filtreme sayfasindaki "Marka" yazisinin yanindaki secim sayisi dogrulanir

    When filtreleme sayfasindaki "Fiyat" butonuna tiklar ve secim yapar
      |8000       |
      |9000       |

    And filtreleme sayfasindaki "Sonuçları Göster" butonuna tiklanir
    Then filtreleme sonrasi urunlerin listelendigi dogrulanir
    When sayfadaki "Sırala" fonksiyonu ile urunler "Ucuzdan Pahalıya" olarak siralanir ve fiyatlarin siralanmasi dogrulanir
    Then filtreleme sonrasi urun fiyatlarinin secilen aralikta oldugu dogrulanir
    When 1. urunun detay sayfasina gidilir ve urun bilgileri goruntulenir
    Then urun detay sayfasindaki baslik ve fiyat bilgisi dogrulanir
    When urun sepete eklenir
    Then sepete basariyla gecilir ve urun bilgileri dogrulanir







