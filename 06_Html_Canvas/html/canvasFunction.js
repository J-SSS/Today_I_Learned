

const box = document.querySelector(".accordion-header").clientWidth-40

////////////////// 캔버스 생성용 클래스 //////////////////
class CanvasCreate {
  id;
  canvas; ctx;
  layerArr = []; //레이어 구현용 배열
  activatedTool; //활성화 툴 체크용
  pageSize = box/1200; //페이지 사이즈 변화에 따른 배율 조졍용(아직)
  currentScale = 1.0; // 줌기능 배율 조졍용
  currentCanvas = new Image(); //배율조정시 리로드용
  defaultBack = new Image();

  constructor() {
    // 캔버스 본체
    this.canvas = document.createElement("canvas");
    this.ctx = this.canvas.getContext("2d")

    // 캔버스 초기화
    this.init()
  }
  init(){
    // 스타일 관련 초기 설정
    this.canvas.width = 1200;
    this.canvas.height = 500;
    this.ctx.strokeStyle = "black";
    this.ctx.lineWidth = 0.5;
    this.ctx.scale(1.0,1.0)
    this.ctx.translate(0,0)
    this.ctx.setLineDash([0,0])
    this.ctx.save();

    // 버튼활성화 및 배경디자인 생성
    this.pageLiner(this.canvas.width,this.canvas.height)
    this.toolActivation()
    this.colorBtn()

  }

  // 배율 조정 및 윈도우 사이즈 조절시 수정된 좌표값 생성 함수
  xy(xy){
    return xy/this.pageSize
  }
  xyAll(xy){
    return xy/this.pageSize/this.currentScale
  }



  // 캔버스 기본 배경
  pageLiner(width,height){
    let x = 0;
    let y = 0;
    while (x<=height){
      this.ctx.beginPath();
      if(x===0 || height-x===0){
        this.ctx.strokeStyle="black"
        this.ctx.lineWidth=1.0;
      } else if(x%50===0){
          this.ctx.strokeStyle="gray"
          this.ctx.lineWidth=0.2;
      } else{
        this.ctx.strokeStyle="gray"
        this.ctx.lineWidth=0.1;
      }
      this.ctx.moveTo(0, x);
      this.ctx.lineTo(width, x);
      this.ctx.stroke();
      x+=10;
    }
    while (y<=width){
      this.ctx.beginPath();
      if(y===0 || y===width){
        this.ctx.strokeStyle="black"
        this.ctx.lineWidth=1.0;
      } else if(y%50===0){
        if(y/50===12){
          this.ctx.strokeStyle="red"
          this.ctx.lineWidth=0.3;
        } else {
          this.ctx.strokeStyle="gray"
          this.ctx.lineWidth=0.2;
        }
      } else{
        this.ctx.strokeStyle="gray"
        this.ctx.lineWidth=0.1;
      }
      this.ctx.moveTo(y, 0);
      this.ctx.lineTo(y, height);
      this.ctx.stroke();
      y+=10;
    }
    this.currentCanvas.src = this.canvas.toDataURL();
    this.defaultBack.src = this.canvas.toDataURL();
    this.canvasRestore();

  }

  // //스템프 툴
  // stampTool() {
  //   let co = this
  //   function drawHandler(e){
  //     if(co.activatedTool!=="stamp"){
  //       co.canvas.removeEventListener("mousedown",drawHandler)
  //     } else {
  //       let imgElem = new Image();
  //       let imgSrc = "../img/star.png";
  //       imgElem.src = imgSrc;
  //       co.ctx.drawImage(imgElem, co.xy(e.offsetX-25), co.xy(e.offsetY-25))
  //       co.layerPush("img",[e.offsetX,e.offsetY],undefined,undefined, imgSrc)
  //     }
  //   }
  //   this.canvas.addEventListener("mousedown",drawHandler)
  // };

  //사각형 툴
  rectTool(){
    let co = this;
    let moveHandler;
    function drawHandler(e) {
      if(co.activatedTool!=="rect"){
        this.removeEventListener("mousedown", drawHandler
        )
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
          co.ctx.beginPath();
          co.ctx.lineWidth=2;
          co.ctx.strokeStyle="grey";
          co.ctx.setLineDash([10, 10]);
          co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
          co.ctx.drawImage(co.currentCanvas,0,0);
          co.ctx.strokeRect(
            co.xy(startX),
            co.xy(startY),
            co.xy(e.offsetX)-co.xy(startX),
            co.xy(e.offsetY)-co.xy(startY));
          co.ctx.stroke()
        })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
          co.ctx.drawImage(co.currentCanvas,0,0);
          co.ctx.beginPath();
          co.ctx.fillRect(
            co.xy(startX),
            co.xy(startY),
            co.xy(e.offsetX)-co.xy(startX),
            co.xy(e.offsetY)-co.xy(startY));
          co.ctx.stroke();
          co.layerPush("rect",[startX,startY],[e.offsetX,e.offsetY]);
        },{once:true});
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);
  };


  //스템프 툴
  stampTool() {
    let co = this
    let moveHandler;
    let imgElem = new Image();
    let imgSrc = "../img/star.png";
    imgElem.src = imgSrc;
    function drawHandler(e){
      if(co.activatedTool!=="stamp"){
        co.canvas.removeEventListener("mousedown",drawHandler)
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
          co.ctx.beginPath();
          co.ctx.lineWidth=2;
          co.ctx.strokeStyle="grey";
          co.ctx.setLineDash([10, 10]);
          co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
          co.ctx.drawImage(co.currentCanvas,0,0);
          co.ctx.strokeRect(
            co.xy(startX),
            co.xy(startY),
            co.xy(e.offsetX)-co.xy(startX),
            co.xy(e.offsetY)-co.xy(startY));
          co.ctx.stroke()
        })

        // drawImage(image, dx, dy)
        // drawImage(image, dx, dy, dWidth, dHeight)
        // drawImage(image, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight)
        // s : 원본이미지 기준(스플리트) d: 기존

        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
          co.ctx.drawImage(co.currentCanvas,0,0);
          co.ctx.drawImage(imgElem,
                            co.xy(startX),
                            co.xy(startY),
                            co.xy(e.offsetX)-co.xy(startX),
                            co.xy(e.offsetY)-co.xy(startY));
          co.layerPush("img",[startX,startY],[e.offsetX,e.offsetY],undefined, imgSrc)

        },{once:true});
      }
    }
    this.canvas.addEventListener("mousedown",drawHandler)
  };


  // 셀렉터 툴
  selectorTool(){
    let co = this;
    let selectedLayer = null;

      function selectHandler(e){
        let eX = e.offsetX;
        let eY = e.offsetY;

        let sPath;
        let rPath; //확대축소 및 회전을 위한 클릭요소 위치를 저장하기 위한 변수

        // 레이어 역순으로 반복해서 가장 위에 덮인 레이어 선택
        while (selectedLayer == null){
        for(let i = co.layerArr.length-1 ; i>=0 ; i--){
          /*
            마우스클릭 위치(e.offset)는 pageSize을 보정하지 않았기때문에
            레이어의 range도 currentScale과 요소 자체 scale만 보정해서 선택여부 파악해주고
            나중에 그려줄때는 pageSize까지 보정해야 원하는 위치에 제대로 그려짐
           */

          let scale = co.layerArr[i].scale;
          let minX = co.layerArr[i].range[0][0]*co.currentScale/scale;
          let minY = co.layerArr[i].range[0][1]*co.currentScale/scale;
          let maxX = co.layerArr[i].range[1][0]*co.currentScale/scale;
          let maxY = co.layerArr[i].range[1][1]*co.currentScale/scale;
          if((eX>=minX && eX<=maxX) && (eY>=minY && eY<=maxY))
          {
                co.ctx.beginPath();
                co.ctx.strokeStyle="black";
                co.ctx.lineWidth=1;
                co.ctx.setLineDash([5, 5]);
                co.ctx.strokeRect(
                  co.xy(co.layerArr[i].range[0][0]*co.currentScale/co.layerArr[i].scale)-20,
                  co.xy(co.layerArr[i].range[0][1]*co.currentScale/co.layerArr[i].scale)-20,
                  co.xy((co.layerArr[i].range[1][0]-co.layerArr[i].range[0][0])*co.currentScale/co.layerArr[i].scale)+40,
                  co.xy((co.layerArr[i].range[1][1]-co.layerArr[i].range[0][1])*co.currentScale/co.layerArr[i].scale)+40);
                co.ctx.fillStyle="black";

                // 마우스 이벤트에 pageSize를 보정하지 않기 때문에 클릭요소 좌표에도 보정해 줄 필요는 없다

                if(sPath===undefined){
                  sPath = new Path2D();
                  sPath.rect(minX-30, minY-30, 20,20);
                  sPath.rect(minX-30, maxY+10, 20,20);
                  sPath.rect(maxX+10, minY-30, 20,20);
                  sPath.rect(maxX+10, maxY+10, 20,20);
                  sPath.closePath();
                } else {
                  sPath=undefined
                };

                if(rPath===undefined) {
                  rPath = new Path2D();
                  rPath.arc(maxX + 60, (maxY + minY) / 2, 10, 0, 2 * Math.PI);
                  rPath.closePath()
                } else {
                  rPath=undefined
                }

                co.ctx.fillRect(co.xy(minX)-25, co.xy(minY)-25, 10,10);
                co.ctx.fillRect(co.xy(minX)-25, co.xy(maxY)+15, 10,10);
                co.ctx.fillRect(co.xy(maxX)+15, co.xy(minY)-25, 10,10);
                co.ctx.fillRect(co.xy(maxX)+15, co.xy(maxY)+15, 10,10);

                co.ctx.fillStyle="white";
                co.ctx.fillRect(co.xy(minX)-23, co.xy(minY)-23, 6,6);
                co.ctx.fillRect(co.xy(minX)-23, co.xy(maxY)+17, 6,6);
                co.ctx.fillRect(co.xy(maxX)+17, co.xy(minY)-23, 6,6);
                co.ctx.fillRect(co.xy(maxX)+17, co.xy(maxY)+17, 6,6);

                co.ctx.setLineDash([5, 5]);
                co.ctx.moveTo(co.xy(maxX)+20,co.xy((maxY+minY)/2));
                co.ctx.lineTo(co.xy(maxX)+70,co.xy((maxY+minY)/2));
                co.ctx.stroke();

                co.ctx.beginPath();
                co.ctx.fillStyle="black";
                co.ctx.setLineDash([0, 0]);
                co.ctx.arc(co.xy(maxX)+70, co.xy((maxY+minY)/2), 6, 0, 2*Math.PI);
                co.ctx.stroke();
                co.ctx.fill();

                co.canvasRestore();

                selectedLayer = i;
                co.canvas.addEventListener("mousedown",moveHandler)
            } //if
          } //for
        } //while
      } //select

      function moveHandler(e){
        let isMove = true;
        let startX = e.offsetX;
        let startY = e.offsetY;
        co.canvasClear();
        co.layerLoad(selectedLayer,true);
        co.currentCanvas.src = co.canvas.toDataURL();


        co.canvas.addEventListener("mousemove",(e)=>{
          if(isMove){
            co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
            co.ctx.drawImage(co.currentCanvas,0,0);
            co.ctx.fillRect(e.offsetX,e.offsetY,50,50)

          }
        })

        co.canvas.addEventListener("mouseup",(e)=>{
          if(isMove){
            console.log("정지"+e.offsetX,e.offsetY)
            console.log("마우스 업")
            isMove = false;
          }
        })
      }

      this.canvas.addEventListener("mousedown",selectHandler)
  };
  textTool(){};
  palateTool(){};
  postTool(){};

  //직선 툴
  lineTool(){
    let co = this;
    let moveHandler;
    function drawHandler(e) {
      if(co.activatedTool!=="line"){
        this.removeEventListener("mousedown", drawHandler)
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
            co.ctx.strokeStyle="grey";
            co.ctx.lineWidth=2;
            co.ctx.setLineDash([10, 10]);
            co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
            co.ctx.drawImage(co.currentCanvas,0,0);
            co.ctx.beginPath();
            co.ctx.moveTo(co.xy(startX),co.xy(startY));
            co.ctx.lineTo(co.xy(e.offsetX),co.xy(e.offsetY));
            co.ctx.stroke();
        })
          this.addEventListener("mouseup",(e)=>{
            this.removeEventListener("mousemove", moveHandler);
            co.canvasRestore();
            co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
            co.ctx.drawImage(co.currentCanvas,0,0);
            co.ctx.beginPath();
            co.ctx.moveTo(co.xy(startX),co.xy(startY));
            co.ctx.lineTo(co.xy(e.offsetX),co.xy(e.offsetY));
            co.ctx.stroke();
            co.layerPush("line",[startX,startY],[e.offsetX,e.offsetY])
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);

  };
  //사각형 툴
  rectTool(){
    let co = this;
    let moveHandler;
    function drawHandler(e) {
      if(co.activatedTool!=="rect"){
        this.removeEventListener("mousedown", drawHandler
        )
      } else {
        let startX = e.offsetX;
        let startY= e.offsetY;
        this.addEventListener("mousemove", moveHandler = function (e){
          co.ctx.beginPath();
          co.ctx.lineWidth=2;
          co.ctx.strokeStyle="grey";
          co.ctx.setLineDash([10, 10]);
          co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
          co.ctx.drawImage(co.currentCanvas,0,0);
          co.ctx.strokeRect(
                                        co.xy(startX),
                                        co.xy(startY),
                                      co.xy(e.offsetX)-co.xy(startX),
                                      co.xy(e.offsetY)-co.xy(startY));
          co.ctx.stroke()
        })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          co.ctx.clearRect(0,0,co.canvas.width,co.canvas.height);
          co.ctx.drawImage(co.currentCanvas,0,0);
          co.ctx.beginPath();
          co.ctx.fillRect(
                                    co.xy(startX),
                                    co.xy(startY),
                                  co.xy(e.offsetX)-co.xy(startX),
                                  co.xy(e.offsetY)-co.xy(startY));
          co.ctx.stroke();
          co.layerPush("rect",[startX,startY],[e.offsetX,e.offsetY]);
        },{once:true});
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);
  };




  //펜 툴
  penTool(){
    let co = this;
    let moveHandler;
    function drawHandler(e) {
      if(co.activatedTool!=="pen"){
        this.removeEventListener("mousedown", drawHandler);
      } else {
          let startX = e.offsetX;
          let startY = e.offsetY;
          let pathArray = [];
          co.ctx.beginPath()
          co.ctx.moveTo(co.xy(startX),co.xy(startY));
          this.addEventListener("mousemove", moveHandler = function (e){
              pathArray.push([e.offsetX,e.offsetY])
              co.ctx.lineTo(co.xy(e.offsetX),co.xy(e.offsetY));
              co.ctx.stroke()
          })
        this.addEventListener("mouseup",(e)=>{
          this.removeEventListener("mousemove", moveHandler)
          co.layerPush("pen",[startX,startY],undefined, pathArray)
        },{once:true})
      }
    }
    this.canvas.addEventListener("mousedown", drawHandler);

  };
  canvasRestore(){
    let co = this;
    co.ctx.restore();
    co.ctx.save();
  }
  canvasClear(){
    let co = this;
    co.ctx.clearRect(0, 0, co.canvas.width, co.canvas.height);
  }
  canvasSave(){
    let co = this;
    co.currentCanvas.src = co.canvas.toDataURL();
  }


  // 경로값 저장용
  layerPush(
    type,
    moveTo = undefined,
    lineTo = undefined,
    path = undefined,
    src = undefined)
  {
    let co = this;

    let tempObj = {
      type : type,
      strokeStyle : co.ctx.strokeStyle,
      lineWidth : co.ctx.lineWidth,
      scale : co.currentScale,
      moveTo : moveTo,
      lineTo : lineTo,
      path : path,
      src : src,
      range : [],
      pageSize : co.pageSize,

    }
    function findRange(arr){
      let maxX=0, maxY=0, minX=1200, minY=500;
      arr.forEach((xy)=>{
        if(xy[0]>maxX) maxX=xy[0];
        if(xy[1]>maxY) maxY=xy[1];
        if(xy[0]<minX) minX=xy[0];
        if(xy[1]<minY) minY=xy[1];
      })
      return [[minX,minY],[maxX,maxY]]
    }
    // 범위 저장용
    if(type==="line" || type==="rect") {
      tempObj.range = findRange([moveTo,lineTo]);
    } else if(type==="pen") {
      tempObj.range = findRange(path);
    }

    let socket = JSON.stringify(tempObj)
    stomp.send('/pub/plan/canvas', {}, JSON.stringify({roomId: roomId, path: socket, writer: username}));
    co.currentCanvas.src = co.canvas.toDataURL()
    return co.layerArr.push(tempObj);

  }
  receiver(path) {
    let co = this;
    let c = JSON.parse(path);
    co.loadFunction(co,c,true);
  }


  layerLoad(index=undefined,rest=true){
    let co = this;
    let arr = [];

    co.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    co.ctx.scale(co.currentScale,co.currentScale)
    co.ctx.drawImage(co.defaultBack,0,0);

    if(index===undefined && rest == true){ arr = co.layerArr;}
    else if(index !== undefined && rest === true){ arr = co.layerArr.filter((c,i)=>{return i !== index });}
    // else if(index !== undefined && rest === false){ arr.push())}

    arr.forEach((c)=>{
      co.loadFunction(co,c);
    })
    co.canvasRestore();
  }
  loadFunction(co,c,receive=false){
    let obj = this;
    co.ctx.strokeStyle=c.strokeStyle;
    co.ctx.lineWidth=c.lineWidth;

    console.log(co.pageSize)
    function modi(xy){
      if(receive === false) return xy;
      else if(receive === true) return xy/c.pageSize;
    }

    co.ctx.beginPath();
    console.log(c.moveTo[0]/c.scale, c.moveTo[1]/c.scale)
    console.log(modi(c.moveTo[0])/c.scale, modi(c.moveTo[1])/c.scale)
    co.ctx.moveTo(modi(c.moveTo[0])/c.scale,modi(c.moveTo[1])/c.scale)
    if(c.type==="line") {
      co.ctx.lineTo(modi(c.lineTo[0])/c.scale,modi(c.lineTo[1])/c.scale)
      co.ctx.stroke();
    } else if(c.type==="pen"){
      c.path.forEach((p)=>{
        co.ctx.lineTo(modi(p[0])/c.scale,modi(p[1])/c.scale);
        co.ctx.stroke();
      })
    } else if(c.type==="rect"){
      co.ctx.fillRect(
        modi(c.moveTo[0])/c.scale,
        modi(c.moveTo[1])/c.scale,
        modi(c.lineTo[0])/c.scale-modi(c.moveTo[0])/c.scale,
        modi(c.lineTo[1])/c.scale-modi(c.moveTo[1])/c.scale
      );
    }
  }



  //사이드바 각 버튼에 기능부여
  toolActivation(){
    const tools = ["stamp","pen","line","selector","rect","text","palate","post"];

    tools.forEach((tool)=>{
      document.getElementById(tool+"Btn").addEventListener("click",()=>{
        if(this.activatedTool===tool) return;
        else {
          this.activatedTool=tool;
          this[tool+"Tool"]();
        }
      })
    });

    // 테스트중인 기능들
    ///////배열 테스트용/////
    document.getElementById("removeBtn").addEventListener("click", ()=>{
      this.activatedTool="default";
      this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height);
    });
    ///////배열 테스트용/////
    document.getElementById("loadBtn").addEventListener("click", ()=>{
      this.layerLoad()
    });


    ///////배율조정 테스트용/////
    document.getElementById("plusBtn").addEventListener("click",()=>{
      if (this.currentScale<5.00) {
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.currentScale *= 1.2;
        this.ctx.scale(1.2, 1.2);
        this.ctx.drawImage(this.currentCanvas,0,0);
        this.ctx.scale(1/1.2, 1/1.2);
        this.currentCanvas.src = this.canvas.toDataURL()
      }
    });
    document.getElementById("minusBtn").addEventListener("click",()=>{
      if (this.currentScale>1.05){
        this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height);
        this.currentScale/=1.2;
        this.layerLoad();

        this.currentCanvas.src = this.canvas.toDataURL()
      }
    })
  };

  colorBtn(){
    buttons.forEach((content) => {
      let button = document.querySelector(`.${content}`);
      button.onclick = () => {
        this.ctx.strokeStyle = content;
        this.ctx.fillStyle = content;
        this.ctx.save();
      };
    });
  }



};//클래스 닫기

document.getElementById("settingBtn").addEventListener("click",()=>{
  let CCC = document.getElementById("123");
  console.log(CCC)
  let CCco = CCC.getContext("2d");
  CCco.strokeRect(0,0,50,50);
})


//////////////////////////////////////////////////////////////////////////////////////////////
// 여기서부터는 테스트용..

//포인터 변경기능
// canvas.style.cursor = 'pointer';


//텍스트 입력기능 부분
// var hasInput = false;
//
// canvas.onclick = function(e) {
//   if (hasInput) return;
//   addInput(e.clientX, e.clientY);
// }
//
//
// let font = '14px sans-serif';
// function addInput(x, y) {
//   var input = document.createElement('input');
//
//   input.type = 'text';
//   input.style.position = 'fixed';
//   input.style.left = (x - 4) + 'px';
//   input.style.top = (y - 4) + 'px';
//
//   input.onkeydown = handleEnter;
//
//   document.body.appendChild(input);
//
//   input.focus();
//
//   hasInput = true;
// }
//
// function handleEnter(e) {
//   let keycode = e.keycode;
//   if (keycode === 13) {
//     drawText(this.value, 100, 100);
//     document.body.removeChild(this);
//     hasInput = false;
//   }
// }
//
// function drawText(txt, x, y) {
//   // context.textBaseline = 'top';
//   context.textAlign = 'left';
//   context.font = font;
//   context.fillText(txt, x + 30, y + 30);
// }


