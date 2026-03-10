#タイトル 「MyReserve」

## アプリ概要
MyReserveは、小規模店舗向けの予約・会員管理システムです。
ユーザーはログイン後、店舗の新着情報を閲覧することができ、予約フォームから来店予約を行うことが出来ます

管理側は管理画面から予約情報を確認できるほか、予約内容の変更や削除を行うことが可能です。
また、店舗からのお知らせを投稿・編集・削除する機能も備えています。

ユーザーは会員ランクをアップグレードすることで特典を受け取る事が出来ます。(※アップグレードには料金が発生する想定です。)

---

## 開発背景
小規模店舗の売り上げ向上を目的として開発しました。

近年、店舗の情報発信はSNSが主流になっていますが、タイムラインが流れてしまうことで
重要な情報を見逃してしまうケースがあります。

そこで、ユーザーがログインすることで店舗からのお知らせを確実に確認できる仕組みを作ることで、
情報伝達の機会損失を防ぎ、来店促進につながるシステムを目指しました。

---

## 使用技術

-Java
-Spring Boot
-Thymeleaf
-Bootstrap
-MyBatis

---

## 機能一覧

### ユーザー側
-ログイン
-お知らせ機能
-予約登録
-会員ランクアップグレード/ダウングレード(未実装)

### 管理側
-管理者ログイン
-お知らせ投稿
-お知らせ編集
-お知らせ削除
-予約一覧確認
-予約編集
-予約削除

※管理画面ではセキュリティー・プライバシーの観点から会員情報を直接閲覧できない設計にしています。

## 画面一覧

-トップ画面
<img width="1820" height="981" alt="Image" src="https://github.com/user-attachments/assets/4f498212-cd83-417f-a77b-b6b8cef5d1df" />
-ユーザーログイン画面
<img width="1820" height="983" alt="Image" src="https://github.com/user-attachments/assets/d0a4d6d6-c3ce-49db-b72f-204da00b2827" />
-ユーザーホーム画面
<img width="1829" height="985" alt="Image" src="https://github.com/user-attachments/assets/9a7fd2df-ccc6-407d-b623-f9d249759f34" />
-新着情報一覧画面
<img width="1825" height="994" alt="Image" src="https://github.com/user-attachments/assets/6a176c75-e046-4bf4-99d8-4d39000cf4ba" />
-予約画面
<img width="1827" height="990" alt="Image" src="https://github.com/user-attachments/assets/003b20ee-62bf-4774-9b4b-a155b019a2fd" />
-管理者ログイン画面
<img width="1836" height="982" alt="Image" src="https://github.com/user-attachments/assets/fb48d90e-7cf5-4f9f-ac05-41a69e843ff6" />
-管理者ホーム画面
<img width="1823" height="988" alt="Image" src="https://github.com/user-attachments/assets/f10fff4e-afe1-45f2-b8ea-e2f8488bc075" />
-お知らせ一覧画面
<img width="1834" height="978" alt="Image" src="https://github.com/user-attachments/assets/97b1c548-221c-4499-9f65-c12e1c239c7f" />
-お知らせ追加画面

-予約管理画面

## ER図

## 工夫した点

### 不正アクセス対策
ログインしていない状態でURLを直接入力してアクセスした場合、該当ページを表示せずログイン画面へリダイレクトする設計にしました。
また、ログイン済みユーザーがログイン画面へアクセスした場合はホーム画面へリダイレクトすることで、ユーザー体験の向上を意識しました。

### ユーザー目線の設計
ユーザー側・管理側それぞれの利用者の視点に立ち、操作が分かりやすい画面構成を意識して設計しました。

## 今後の改善案

### UI改善
現在はBootstrapを使用したシンプルなUIのため、より見やすく直観的に操作できるデザインへ改善したいと考えています。

### 認証機能の改善
現在はログインIDとしてユーザー名を使用しているため、重複するとエラーが発生する可能性があります。
今後は会員登録機能を実装し、メールアドレス一意のログインIDとして利用する予定です。

また、現在は共通パスワードを使用しているため、ユーザーごとのパスワードを設定できる認証方式へ改善し、セキュリティを向上させたいと考えています。
