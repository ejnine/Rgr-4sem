let arr = [];


let rand = (min, max) =>{
    let r = min - 0.5 + Math.random() * (max - min + 1);
    return Math.round(r)
}


for (let i = 0; i < 10; i++) {
    arr.push(rand(0 , 10));

}

console.log(arr)

console.log("!")

arr = arr.sort((a, b)=>{
    return a - b;
});

console.log(arr)