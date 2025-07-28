
Feature: Kullanıcı Giriş İşlemi

  Background:
    Given kullanici uygulamaya girer
    And "hediye & ekstra" islemini secer
    When alt menuden "hesabım" iconuna tiklar
    And "Üye Girişi" butonuna tiklar
  @login
  Scenario: Geçerli mail ve şifre bilgileri ile başarılı giriş yapılır
    When test datadaki 1.sheetteki "gecerli" mail ve "gecerli" sifre ile hesaba giris yapilir
    Then kullanici giris isleminin "basarili" oldugunu dogrular

  Scenario: Geçerli mail, geçersiz şifre bilgileri ile giriş yapamaz
    When test datadaki 1.sheetteki "gecerli" mail ve "gecersiz" sifre ile hesaba giris yapilir
    Then kullanici giris isleminin "basarisiz" oldugunu dogrular

  Scenario: Geçersiz mail, geçerli şifre bilgileri ile giriş yapamaz
    When test datadaki 1.sheetteki "gecersiz" mail ve "gecerli" sifre ile hesaba giris yapilir
    Then kullanici giris isleminin "basarisiz" oldugunu dogrular

  Scenario: Geçersiz mail, geçersiz şifre bilgileri ile giriş yapamaz
    When test datadaki 1.sheetteki "gecersiz" mail ve "gecersiz" sifre ile hesaba giris yapilir
    Then kullanici giris isleminin "basarisiz" oldugunu dogrular