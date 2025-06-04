const asyncTask = new Promise((resolve, reject) => {
 setTimeout(() => resolve(1), 1000); // 1초 후에 1을 반환
});

 asyncTask
 .then(result => {
 console.log(result); // 1
 return result + 1;
 })
 .then(result => {
 console.log(result); // 2
 return result + 1;
 })
 .then(result => {
 console.log(result); // 3
 })
 .catch(error => {
 console.error(error); // 에러 발생 시 출력
    });


console.log("하이미디어");