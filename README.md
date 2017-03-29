# 概要
簡単で実用的なSpringBootのサンプルが作りたいと思い作りました。
Javaが動く環境であれば、ダウンロードしてgradlew bootRunコマンドですぐに起動します。
住所検索用API（レスポンスはJSONかJSONP形式）です。

# 使っているフレームワーク
* [SpringBoot](https://projects.spring.io/spring-framework/)
* [Thymeleaf](http://www.thymeleaf.org/)
* [Doma](https://doma.readthedocs.io/ja/stable/)
* [Bootstrap](http://getbootstrap.com/)
* [H2 Database](http://www.h2database.com/html/main.html)

# 使い方
1. Githubからプロジェクトを[ダウンロード](https://github.com/Takumon/JapaneseAddressSeacher/archive/master.zip)。
2. プロキシ環境下の場合はプロジェクトルートにあるgradle.propertiesを編集。
3. コマンドプロンプトでプロジェクトルートに移動し下記コマンドを実行。
```
gradlew bootRun
```

なおポートを(デフォルトは8080)を変更する場合は下記を実行する。
```
gradlew　←いったんjarを生成する
java -jar build/libs/address-searcher-0.1.0.jar --server.port=8081　←ポートはお好きな番号に
```

# APIの仕様
| HTTPメソッド | URL | リクエストパラメータ | 機能概要 |
|------------|------------|-------------|-------------|
| GET | /api/v1/jp/states  | なし | 都道府県情報一覧を取得する |
| GET | /api/v1/jp/states/{stateName}  | なし | 指定された都道府県の都道府県情報を取得する |
| GET | /api/v1/jp/states/{stateName}/cities  | なし | 指定された都道府県の市区町村情報一覧を取得する |
| GET | /api/v1/jp/states/{stateName}/cities/{cityName}  | なし | 指定された市区町村の情報を取得する |
| GET | /api/v1/jp/states/{stateName}/cities/{cityName}/sections  | なし | 指定された市区町村の町域情報一覧を取得する |
| GET | /api/v1/jp/states/{stateName}/cities/{cityName}/sections/{sectionName}  | なし | 指定された町域の町域情報を取得する |
| GET | /api/v1/jp/states/{stateName}/cities/{cityName}/sections/{sectionName}/addresses  | なし | 指定された町域の住所情報一覧を取得する |
| GET | /api/v1/jp/addresses/{addressZipCode}  | なし | 指定された郵便番号("000-0000"のような形式)の住所情報一覧を取得する |
| GET | /api/v1/jp/search  | q:キーワード,sort:ソート順(※1),offset:検索開始位置,limit:最大検索数 | 都道府県名、市区町村名、町域名のいずれかが指定されたキーワードに部分一致する住所情報一覧を取得する |

## (※1)ソート順
住所情報のプロパティ名を指定する。
複数指定する場合はカンマ区切り。デフォルトは昇順で、プロパティ名の接頭辞に-をつけると降順になる。
例えば、第一ソートキーは都道府県名カナを昇順、第二ソートキーに市区町村カナを降順、第三ソートキーに町域カナを昇順で指定する場合は下記のようになる。
```
http:/localhost:8080/api/v1/jp/search?sort=stateKana,-cityKana,sectionKana
```


# リソースの仕様
## 都道府県
	{
		"stateId": integer,
		"stateName": string,
		"stateKana": string,
		"cityCount": integer
	}

| プロパティ名 | 型 | 説明 | 補足 |
|------------|------------|-------------|-------------|
| stateId | integer | 都道府県ID |  |
| stateName | string | 都道府県名 | 漢字 |
| stateKana | string | 都道府県カナ | 全角カタカナ |
| cityCount | string | 市区町村数 | 都道府県に含まれる市区町村情報の数 |

## 市区町村	
	{
		"cityId": integer,
		"cityName": string,
		"cityKana": string,
		"stateId": integer,
		"stateName": string,
		"stateKana": string,
		"sectionCount": integer
	}

| プロパティ名 | 型 | 説明 | 補足 |
|------------|------------|-------------|-------------|
| cityId | integer | 市区町村ID |  |
| cityName | string | 市区町村名 | 漢字 |
| cityKana | string | 市区町村カナ | 全角カタカナ |
| stateId | integer | 都道府県ID |  |
| stateName | string | 都道府県名 | 漢字 |
| stateKana | string | 都道府県カナ | 全角カタカナ |
| sectionCount | string | 町域数 | 市区町村に含まれる町域情報の数 |

## 町域	
	{
		"sectionId": integer,
		"sectionName": string,
		"sectionKana": string,
		"cityId": integer,
		"cityName": string,
		"cityKana": string,
		"stateId": integer,
		"stateName": string,
		"stateKana": string,
		"addressCount": integer
	}

| プロパティ名 | 型 | 説明 | 補足 |
|------------|------------|-------------|-------------|
| sectionId | integer | 町域ID |  |
| sectionName | string | 町域名 | 漢字 |
| sectionKana | string | 町域カナ | 全角カタカナ |
| cityId | integer | 市区町村ID |  |
| cityName | string | 市区町村名 | 漢字 |
| cityKana | string | 市区町村カナ | 全角カタカナ |
| stateId | integer | 都道府県ID |  |
| stateName | string | 都道府県名 | 漢字 |
| stateKana | string | 都道府県カナ | 全角カタカナ |
| addressCount | string | 住所数 | 町域に含まれる住所情報の数 |

## 住所
	{
		"id": integer,
		"stateId": integer,
		"cityId": integer,
		"sectionId": integer,
		"addressZipCode": string,
		"officeFlg": integer,
		"deleteFlg": integer,
		"stateName": string,
		"stateKana": string,
		"cityName": string,
		"cityKana": string,
		"sectionName": string,
		"sectionKana": string,
		"sectionMemo": string,
		"kyotoStreet": string,
		"blockName": string,
		"blockKana": string,
		"memo": string,
		"officeName": string,
		"officeKana": string,
		"officeAddress": string,
		"newId": integer
	}

| プロパティ名 | 型 | 説明 | 補足 |
|------------|------------|-------------|-------------|
| id | integer | 住所ID |  |
| stateId | integer | 都道府県ID |  |
| cityId | integer | 市区町村ID |  |
| sectionId | integer | 町域ID |  |
| addressZipCode | string | 郵便番号 | 形式は"000-0000"のようにハイフンが入る |
| officeFlg | integer | 事業所フラグ | 事業所データの場合は1、それ以外は0 |
| deleteFlg | integer | 廃止フラグ  | 2010年１月更新分のデータから廃止された住所は1、それ以外は0 |
| stateName | string | 都道府県名 | 漢字 |
| stateKana | string | 都道府県カナ | 全角カタカナ |
| cityName | string | 市区町村名 | 漢字 |
| cityKana | string | 市区町村カナ | 全角カタカナ |
| sectionName | string | 町域名 | 漢字 |
| sectionKana | string | 町域カナ | 全角カタカナ |
| sectionMemo | string | 町補足名 | 同じ町域が複数レコードある場合の区別方法(※2) |
| kyotoStreet | string | 京都通り名 | 京都市内で用いられる住所記法 |
| blockName | string | 字丁目 | 小字、丁目、高層ビル各階の名称。町域の後に記載するもの |
| blockKana | string | 字丁目カナ | 字丁目のカナ |
| memo | string | 補足 |  |
| officeName | string | 事業所名 |  |
| officeKana | string | 事業所名カナ |  |
| officeAddress | string | 事業所住所 |  |
| newId | integer | 新住所ID | 廃止された住所で移行先が判明している場合の移行先の住所ID |

### (※2) 町域補足
１．町域が空欄となるとき、以下の内容で区別する。
* "（該当なし）"　他に町域の記載がないときにはここに含まれる
* "（直番地）"　　市区町村名のあとに番地が続く
* "（全域）"　　　一市区町村に対し、一つの郵便番号のみ

２．同じ町域（漢字）でも町域カナが異なるとき、町域補足で区別する。
* "（－チョウ）"　例：愛知県大府市北崎町　（キタサキチョウ）　
* "（－マチ）"　　例：愛知県大府市北崎町　（キタサキマチ）　
  
# 画面の仕様
1. ローカルホスト（http\://localhost:8080/）にアクセスすると住所検索画面が表示される。
![検索画面のキャプチャ（検索前）](https://github.com/Takumon/JapaneseAddressSeacher/blob/master/screen1.png)

2. 郵便番号（999-9999形式）を入力し検索ボタンをクリックする。下記のように住所一覧が表示される。
![検索画面のキャプチャ(検索後)](https://github.com/Takumon/JapaneseAddressSeacher/blob/master/screen2.png)

# 住所データについて
データベースを用意する必要はありません。
H2 Databaseを使っているためアプリ起動時にメモリに読み込みます。
[住所.jp](http://jusyo.jp/csv/new.php)の全国版CSVを加工して初期化SQLを作成しました。
そのため住所の情報は最新ではありません。
