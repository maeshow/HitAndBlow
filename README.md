## HitAndBlow

ヒットアンドブロー

## Overview

コンピュータが選んだ5つの数字の順番と数字を当てるゲーム

## Detail

- コンピュータが選ぶ5つの数字に重複はない
- 順番も数字もあっている場合は「ヒット」
- 順番は間違っているが入っている数字の場合は「ブロー」
- 先頭の数字は0から始まらない
- 3回に一回、使われている数字ヒントとして出す

### インターフェース

#### CUI

実行例

``` console
5桁の数字を入力してください。12345
ヒット：1個、ブロー：1個     
5桁の数字を入力してください。67890
ヒット：3個、ブロー：0個     
5桁の数字を入力してください。17890
ヒット：2個、ブロー：0個
HINT: 使われている数字に6があります。
5桁の数字を入力してください。67845   
ヒット：3個、ブロー：0個     
5桁の数字を入力してください。67840
ヒット：4個、ブロー：0個     
5桁の数字を入力してください。62840
ヒット：4個、ブロー：0個
HINT: 使われている数字に3があります。
5桁の数字を入力してください。63840
おめでとう！7回目で成功♪
```

## Structure Overview

- src/
    - App
        - main()
        - startGame()
        - endGame()
        - createNumberbyRandom(int digit, int randomRange)
        - initAnswer(int count, int[] answers)
        - isDuplicate(int[] answers, int randomNumber)
        - startPlayerRespones(int[] answers)
        - isDivisible(int a, int b)
        - getHintIndex(count)
        - isCorrectAnswer(int[] result)
        - getHitAndBlowCount(int[] input, int[] answers, int result)
        - isBlow(int[] input, int[] answers, int index)
        - isHit(int[] input, int[] answers, int index)
        - getPlayerStdin()
        - getPlayerInput(int input)
        - isCorrectRange(int inputNumber)
        - showResult(int[] result, int count)
    - Messages