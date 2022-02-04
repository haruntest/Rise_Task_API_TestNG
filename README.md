


Merhabalar,

Bu proje Harun ISIK tarafından Rise Technology için hazırlanmıştır.

Trello sitesinde yapılan API test senaryolarının(POST,PUT,GET,DELETE) otomasyon kodunu içerir.
OOP kavramları, Page Object Model(POM) ve TestNG kullanılarak API testlerinin otomasyonu yapılmıştır.
Rest-Asured,jackson-databind(POJO),lombok(POJO) kütüphaneleri kullanılmış olup Java programlama dili
ile kodlanmış Maven demo projesidir.

Testlerin yönetimini kolaylaştırmak amacı ile Uri,basePath,key ve 
token bilgileri configuration properties dosyasında saklandı. 

Before After methotları TestBase sınıfında saklandı. Tüm metodolojiler bakımı kolay ve dinamik bir 
kod elde etmek için kullanıldı. Önemli testleri çalıştırmak için smokeRunner.xml dosyası oluşturuldu.
(istenilmesi durumunda paralel testlerde yapılabilir)

Testin çalışması: Trello kullanıcı adresinizde önce bir board oluşturulur(POST),ardından bir list 
oluşturulur(POST), yeni bir card oluşturulur(POST) ve bu card güncellenir(PUT). Yeni oluşturulan card
GET metodu ile çağrılır.POJO sınıfı yardımıyla ile JSON body'nin sadece istenen kısmı için 
jackson-databind kütüphanesi kullanılarak deserialization işlemi yapıldı. Getter ve Setter methotlarını
otomatik oluşturan lombok kütüphanesi kullanılarak ekrana basıldı(ID,Desc).Board ve card silindi(DELETE). 
 


İyi çalışmalar
Sağlıklı ve Mutlu Kalın
Harun ISIK
isikharun@outlook.com

