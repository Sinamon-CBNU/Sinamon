# Sinamon


branch에 대한 간단한 설명을 적어놓겠습니다.

제가 배운 바로
main branch는 release(배포)를 담당하는 branch이고,
develop branch는 기능구현을 하는 branch입니다.

즉, 우리가 어떤 기능을 추가하거나 수정하고 싶을때는 develop branch에서
새로운 branch를 만들어 작업 한 후, develop branch로 merge시키는 방식을 사용합니다.

그리고, develop branch가 새로 merge됐을때 
이 기능을 배포해야하므로 develop -> main으로 다시 merge시켜줍니다.

main과 develop에는 branch protection을 걸어놨기때문에
merge를 할때 팀원 2명이상의 approve가 있어야지만 merge됩니다.
그만큼 develop과 main은 중요한 branch입니다. 

*******************************
* [➤가장 중요한 것은]
main branch에서 작업하지 말것!!
main branch에서 pull/push를 하지 말것!!
***************************************

main branch는 배포담당 branch인만큼 develop 에서 병합되기만 하지
이 브랜치에서 어떤 작업도 하지 않는 것이 좋습니다.



**********************************************
따라서 우리가 어떤 기능을 추가하거나 수정하고싶을때는
develop branch로 이동한 후, develop branch에서 새로운 branch를 만들어
새로운 branch에서 새로운 파일을 생성하든, 코드를 수정하든 하신다음에
pull/push를 자유롭게하면됩니다.
*************************************************

만약 새로운 branch에서 기능구현이 끝났다
그러면 이제 새로운 branch ->develop으로 merge하고, develop -> main으로 merge시키는 것입니다.




