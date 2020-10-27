## 1、基本属性（4个概念）

### 标签

> 类似于mysql的table，但是统一标签下的节点属性不一定是相同的。

### 属性

> 用于描述节点。类型与mysql中table的字段

### 节点

> 一个节点可以拥有多个属性

### 关系

> 具备开始节点和结束节点，以及关系的方向（支持双向）


## 2、常用语句

### 标签的增查改删

```CQL
# 1.创建标签
create (lable:`后端`)
# 2.查询目前neo4j中所有的标签 （内置函数）
call db.labels
# 3.修改标签 （1.先删除标签；2、新增标签）
match (n:`后端`) remove n:`后端` set n:`后端语言`
# 4.删除标签
match (n:`后端`) remove n:`后端`
# 4.1 删除一个标签下所有的节点以及相关的关系
match (n:`后端语言`) detach delete n;
```

### 节点的增查改删

```CQL
#　1.创建节点
create (n:`后端语言` {name:'java', age: 12})
#  2.查询
#  2.1 根据标签查询节点
#  2.1.1 根据存在指定标签查询节点
match (n:`后端语言`) return n
#  2.1.2 根据不存在指定标签查询节点
match (n) where none(label in labels(n) where label in ['后端语言'])  return n
#  2.1.3 多标签查询（只要能匹配上多个标签中的任意一个就能查询出来）
MATCH (n) WHERE any(label in labels(n) WHERE label in ['后端语言', 'Persion']) RETURN n
#  2.2 根据属性查询节点
#  2.2.1 根据属性准确查询节点
match (n:`后端语言`) where n.name ='java' return n
#  2.2.2 根据属性模糊查询节点
match (n:`后端语言`) where n.name =~'.*j.*' return n
#  2.3 根据属性查询节点、子节点、关系
#  2.4 根据属性查询节点、子节点、关系
#  2.5 孤立点查询
MATCH (n) WHERE NOT (n)--() return n
```



### 属性的增查改删

```CQL
# 1.增加属性
match (n:`后端语言`) where n.name='java'  set n.addjava="测试添加java属性" return n
# 2.查询
# 2.1 模糊查询
match (n:`后端语言`) where n.name =~'.*j.*' return n
# 2.2 准确查询
match (n:`后端语言`) where n.name ='java' return n
# 3. 修改属性
match (n:`后端语言`) where n.name='java'  set n.addjava="测试添加java属性修改" return n
# 4. 删除属性 (删除java下面的addjava属性)
match (n:`后端语言`) where n.name ='java' remove n.addjava return n
```



### 关系的增查改删

```CQL
# 1.增加关系
match (m:`电影`),(d:`导演`) where m.name='功夫' and d.name='周星驰' create (m)-[r:导演{des:'制作电影的灵魂人物'}]->(d) return m,d,r
# 2.查询关系
# 3.修改关系属性
# 4.删除关系
match (m:`电影` {name:'功夫'})-[r:导演]->(n:`导演` {name:'周星驰'}) delete r
```



## 3、关键字

> remove：移除；用于移除标签和属性。



> delete：删除；用户删除节点和关系。



> detach：删除节点和与之关联的所有关系



> MERGE：如果不存在，则创建

## 4、常见场景

