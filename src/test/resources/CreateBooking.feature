Feature: Kullanici olarak otel rezervasyonu olusturabilirim

  Scenario: Kullanici bir otel rezervasyonu olusturabilir ve rezervasyonu silebilir
    Given Kullanici yeni bir rezervasyon olusturuyor
    And Kullanici rezervasyon icin gereken bilgileri veriyor
    When Kullanici otel rezervasyonu yaratiyor
    Then Rezervasyon basarili sekilde olusturuldu
    And Kullanici olusturulan rezervasyonu iptal ediyor