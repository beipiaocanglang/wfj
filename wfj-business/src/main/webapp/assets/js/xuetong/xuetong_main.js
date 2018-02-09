function drawPedigre (nodes,edges,area) {
            var data = {
                nodes:nodes,
                edges:edges
            }
            var options = {
                autoResize: true,
                height: '100%',
                width: '100%',
                layout: {
                    hierarchical: {
                        sortMethod: "directed"
                    }
                },
                groups:{
                    target:{
                        color:{
                            background:'#a3daff',
                            highlight:{
                                background:'#fff0f6'
                            }
                        }
                    },
                    source:{
                        color:{
                            background:'#1b6ec2',
                            highlight:{
                                background:'yellow'
                            }
                        }
                    }
                },
                nodes:{
                    shape:'box'
                },
                edges: {
                    font: {
                        align: 'top',
                        vadjust: 0
                    },
                    arrows: {to : true },
                    smooth:true
                }
            };
            var container = area;
            var network = new vis.Network(container, data, options);
        }