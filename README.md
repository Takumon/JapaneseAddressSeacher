# 概要
SpringBootのサンプルアプリ。
住所検索画面と、住所検索用WebAPI（レスポンスはJSONP形式）を提供する。

# 使っているフレームワーク
* [SpringBoot](https://projects.spring.io/spring-framework/)
* [Thymeleaf](http://www.thymeleaf.org/)
* [Doma](https://doma.readthedocs.io/ja/stable/)
* [Bootstrap](http://getbootstrap.com/)
* [H2 Database](http://www.h2database.com/html/main.html)

# 使い方
1. Githubからプロジェクトをダウンロードする。
2. プロキシ環境下で実行する場合はプロジェクトルートにあるgradle.propertiesを編集する。
3. コマンドプロンプトでプロジェクトルートに移動し下記コマンドを実行。
```
gradlew bootRun
```

※ポート(デフォルトは8080)を変更する場合は下記を実行する。
```
gradlew
java -jar build/libs/address-searcher-0.1.0.jar --server.port=****
```
4. 住所検索画面を開く場合は、ローカルホスト（http\://localhost:8080/）にアクセスする。下記画面が表示される。
![検索画面のキャプチャ（検索前）](https://github.com/Takumon/JapaneseAddressSeacher/blob/master/screen1.png)

5. 郵便番号を入力し検索ボタンをクリックする。下記のように住所一覧が表示される。
![検索画面のキャプチャ(検索後)](https://github.com/Takumon/JapaneseAddressSeacher/blob/master/screen2.png)

6. WebAPIは下記URLでアクセスできる。

| HTTPメソッド | URL | リクエストパラメータ | 機能概要 |
|------------|------------|-------------|-------------|
| GET | /api/states  | なし | 都道府県情報一覧を取得する |
| GET | /api/cities/stateId/{stateId}  | なし | 指定された都道府県IDの市情報一覧を取得する |
| GET | /api/addresses/stateId/{stateId}  | なし | 指定された都道府県IDの住所一覧を取得する |
| GET | /api/sections/cityId/{cityId}  | なし | 指定された市IDの住所一覧を取得する |
| GET | /api/addresses/zipcode/{addressZipCode}  | なし | 指定された郵便番号の住所一覧を取得する |
| GET | /api/addresses  | part | 指定されたキーワードに該当する住所（都道府県名、市名、市区町村名のいずれかに部分一致する住所）一覧を取得する |

# データについて
[住所.jp](http://jusyo.jp/csv/new.php)の全国版CSVを加工して初期化SQLを作成した。
そのため住所の情報は最新ではない。
H2 Databaseを使っているためアプリ起動時にメモリに読み込む。
