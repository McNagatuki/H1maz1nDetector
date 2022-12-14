# H1maz1nDetector

## 概要

- 一定時間放置すると、設定した場所にtpされるプラグイン。
- 「放置」とは、次のように定義している。
  1. 乗り物に乗っておらず、かつx, y, z座標とPitchとYawが不変の場合。
  2. 乗り物に乗っており、かつPitchが不変の場合。
- TP先の半径を指定可能。その範囲にランダムでTPされる。

## 設定可能項目

| 項目名 | 説明 |
| :--: |:---|
| `afk_time` | 放置判定までの時間。動きを止めてからこの時間経過すると、プレイヤーはTPされる。 |
| `destination` | TP先。x, y, z座標で指定する。 |
| `radius` | TP先の範囲。`destination`を中心とする半径`radius`の円の中にプレイヤーはTPされる。 |
|`ignored_players` | TP対象外のプレイヤー。 |

## コマンド

- 上記設定項目の設定を行うコマンドが使用可能。
- コマンド操作方法は[ConfigLib](https://github.com/TeamKun/ConfigLib)によって自動で生成されるものに準じる。

## 備考

- 放置判定の頻度は1秒(20 ticks)である。
- TP先の範囲内にいるプレイヤーは、放置判定の対象外となる。
  これにより、繰り返しの放置判定を防いでいる。
- `destination`のデフォルト値は設定されていない。
  この状態で放置判定を受けたプレイヤーは、自分が今いる場所をTP先として飛ばされる。
  この場合は、放置判定が繰り返し行われる。
- 放置判定に関する既知の問題点
  - マウス操作することなく乗り物に乗り続けると、誤って放置と判定されてしまう
