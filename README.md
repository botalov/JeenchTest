# JeenchTest

Замечания, которые вижу сам:
1. Не использовал "Dagger 2". К сожалению, никогда ранее не работал с данным инмтрементом, и нормально "въехать" за пару часов, которые у меня были, не получилось. Я решил, что лучше уж вообще не буду использовать инструмент, чем буду использовать его совсем неправильно.
2. Можно было лучше реализовать работу с permissions.
3. Не сильно заморачивался над разметкой. Поэтому выглядит так, как выглядит)
4. С Glide можно было получше поработать. Например, вставить заглушки на случай отсутствия изображения по данному адресу и др. (Раньше тоже не работал с данным инструментом. но был небольшой опыт с Picasso).
Загружаемые данные никак не кэшируются, поэтому, например при повороте экрана, данные загружаются каждый раз заново. 

P.S.: Писал в Android Studio 3.0 RC 2. Тестировал на Nexus 5X c Android 8.0 на борту.
