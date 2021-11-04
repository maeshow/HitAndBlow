## HitAndBlow

ヒットアンドブロー

## Overview

コンピュータが選んだ4つの数字の順番と数字を当てるゲーム

## Detail

- コンピュータが選ぶ4つの数字に重複はない
- 順番も数字もあっている場合は「ヒット」
- 順番は間違っているが入っている数字の場合は「ブロー」
- 先頭の数字は0から始まらない

### インターフェース

#### CUI

実行例

``` console
4桁の数字を入力して下さい。1234
ヒット：1個、ブロー：1個
4桁の数字を入力して下さい。5678
ヒット：0個、ブロー：1個
4桁の数字を入力して下さい。2468
ヒット：0個、ブロー：2個
4桁の数字を入力して下さい。4270
ヒット：2個、ブロー：2個
4桁の数字を入力して下さい。4207
おめでとう！5回目で成功♪
```

## Structure Overview

- src/
    - App
        - int INPUT[]
        - int ANSWER[]
        - int RESULT[]
        - main()
        - startGame()
        - endGame()
        - createNumberbyRandom()
        - initAnswer(int count)
        - isDuplicate(int randomNumber)
        - startPlayerRespones()
        - isCorrectAnswer()
        - setHitAndBlowCount()
        - isBlow(int index)
        - isHit(int index)
        - getPlayerInput()
        - setPlayerInput(int input)
        - isCorrectRange(int inputNumber)
        - showResult(int count)
    - Messages