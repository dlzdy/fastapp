/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.Text;
/*     */ 
/*     */ public class XmlUtil
/*     */ {
/*     */   public static final int ELEMENT = 1;
/*     */   public static final int TEXT = 3;
/*     */   public static final int CDATA = 4;
/*     */ 
/*     */   public static String getAttributeValue(DHNode paramDHNode, String paramString1, String paramString2)
/*     */   {
/*  33 */     String str = getAttributeValue(paramDHNode, paramString1);
/*  34 */     if (str == null) {
/*  35 */       str = paramString2;
/*     */     }
/*  37 */     return str;
/*     */   }
/*     */ 
/*     */   public static int getAttributeValue(DHNode paramDHNode, String paramString, int paramInt)
/*     */   {
/*  52 */     int i = paramInt;
/*     */     try {
/*  54 */       i = Integer.parseInt(getAttributeValue(paramDHNode, paramString, String.valueOf(paramInt)));
/*     */     } catch (NumberFormatException localNumberFormatException) {
/*  56 */       localNumberFormatException.printStackTrace();
/*  57 */       i = paramInt;
/*     */     }
/*  59 */     return i;
/*     */   }
/*     */ 
/*     */   public static String getAttributeValue(DHNode paramDHNode, String paramString)
/*     */   {
/*  73 */     if (paramDHNode == null) {
/*  74 */       return null;
/*     */     }
/*  76 */     if ((paramDHNode.mNode instanceof Element)) {
/*  77 */       return ((Element)paramDHNode.mNode).getAttribute(paramString);
/*     */     }
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getText(DHNode paramDHNode)
/*     */   {
/*  93 */     StringBuffer localStringBuffer = new StringBuffer();
/*  94 */     if (paramDHNode == null) {
/*  95 */       return localStringBuffer.toString();
/*     */     }
/*  97 */     if ((paramDHNode.mNode instanceof Element)) {
/*  98 */       Element localElement = (Element)paramDHNode.mNode;
/*  99 */       NodeList localNodeList = localElement.getChildNodes();
/* 100 */       int i = localNodeList.getLength();
/* 101 */       for (int j = 0; j < i; j++) {
/* 102 */         Node localNode = localNodeList.item(j);
/* 103 */         localStringBuffer.append(localNode.getNodeValue());
/*     */       }
/*     */     }
/* 106 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static void setText(DHNode paramDHNode, String paramString)
/*     */   {
/* 116 */     paramDHNode.mNode.setNodeValue(paramString);
/*     */   }
/*     */ 
/*     */   public static String getElementName(DHNode paramDHNode)
/*     */   {
/* 127 */     return paramDHNode.mNode.getNodeName();
/*     */   }
/*     */ 
/*     */   public static ArrayList<DHNode> getElements(DHNode paramDHNode, String paramString)
/*     */   {
/* 138 */     if (paramDHNode == null) return null;
/* 139 */     ArrayList localArrayList = null;
/* 140 */     if ((paramDHNode.mNode instanceof Element)) {
/* 141 */       NodeList localNodeList = ((Element)paramDHNode.mNode).getElementsByTagName(paramString);
/* 142 */       if (localNodeList != null) {
/* 143 */         localArrayList = new ArrayList(2);
/* 144 */         int i = localNodeList.getLength();
/* 145 */         Object localObject = null;
/* 146 */         for (int j = 0; j < i; j++) {
/* 147 */           localArrayList.add(new DHNode(localNodeList.item(j), null));
/*     */         }
/*     */       }
/*     */     }
/* 151 */     return localArrayList;
/*     */   }
/*     */ 
/*     */   public static DHNode getElement(DHNode paramDHNode, String paramString)
/*     */   {
/* 164 */     if (paramDHNode == null) {
/* 165 */       return null;
/*     */     }
/* 167 */     if ((paramDHNode.mNode instanceof Element)) {
/* 168 */       Element localElement = (Element)paramDHNode.mNode;
/* 169 */       NodeList localNodeList = localElement.getElementsByTagName(paramString);
/* 170 */       Node localNode = localNodeList.item(0);
/* 171 */       if (localNode != null) {
/* 172 */         return new DHNode(localNode, null);
/*     */       }
/*     */     }
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */   public static DHNode getChild(DHNode paramDHNode, int paramInt)
/*     */   {
/* 186 */     if (paramDHNode == null) {
/* 187 */       return null;
/*     */     }
/* 189 */     NodeList localNodeList = paramDHNode.mNode.getChildNodes();
/* 190 */     return localNodeList != null ? new DHNode(localNodeList.item(paramInt), null) : null;
/*     */   }
/*     */ 
/*     */   public static int getNodeType(DHNode paramDHNode, int paramInt)
/*     */   {
/* 200 */     if (paramDHNode == null) {
/* 201 */       return -1;
/*     */     }
/* 203 */     return paramDHNode.mNode.getNodeType();
/*     */   }
/*     */ 
/*     */   public static boolean isElement(Object paramObject)
/*     */   {
/* 214 */     if ((paramObject instanceof Element)) {
/* 215 */       return true;
/*     */     }
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   public static DHNode newElement(DHNode paramDHNode, String paramString)
/*     */   {
/* 230 */     Element localElement = paramDHNode.mNode.getOwnerDocument().createElement(paramString);
/* 231 */     return new DHNode(localElement, null);
/*     */   }
/*     */ 
/*     */   public static void addChild(DHNode paramDHNode1, DHNode paramDHNode2, int paramInt)
/*     */   {
/* 255 */     paramDHNode1.mNode.appendChild(paramDHNode2.mNode);
/*     */   }
/*     */ 
/*     */   public static void removeChild(DHNode paramDHNode, int paramInt)
/*     */   {
/* 266 */     paramDHNode.mNode.removeChild(paramDHNode.mNode.getChildNodes().item(paramInt));
/*     */   }
/*     */ 
/*     */   public static void removeChild(DHNode paramDHNode1, DHNode paramDHNode2)
/*     */   {
/* 277 */     if (paramDHNode2 == null) {
/* 278 */       return;
/*     */     }
/* 280 */     paramDHNode1.mNode.removeChild(paramDHNode2.mNode);
/*     */   }
/*     */ 
/*     */   public static void setAttributeValue(DHNode paramDHNode, String paramString1, String paramString2)
/*     */   {
/* 295 */     if (!(paramDHNode.mNode instanceof Element)) {
/* 296 */       return;
/*     */     }
/* 298 */     ((Element)paramDHNode.mNode).setAttribute(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public static DHNode XML_Parser(InputStream paramInputStream)
/*     */   {
/*     */     try
/*     */     {
/* 312 */       DHNode localDHNode = XML_ParserDocument(paramInputStream);
/* 313 */       return new DHNode(((Document)localDHNode.mNode).getDocumentElement(), null);
/*     */     } catch (Exception localException) {
/* 315 */       localException.printStackTrace();
/*     */     }
/* 317 */     return null;
/*     */   }
/*     */ 
/*     */   public static DHNode getRootElement(DHNode paramDHNode) {
/*     */     try {
/* 322 */       if ((paramDHNode.mNode instanceof Document))
/* 323 */         return new DHNode(((Document)paramDHNode.mNode).getDocumentElement(), null);
/*     */     }
/*     */     catch (Exception localException) {
/* 326 */       localException.printStackTrace();
/*     */     }
/* 328 */     return null;
/*     */   }
/*     */ 
/*     */   public static ArrayList<String[]> getAttributes(DHNode paramDHNode)
/*     */   {
/* 340 */     if (paramDHNode == null) {
/* 341 */       return null;
/*     */     }
/* 343 */     if ((paramDHNode.mNode instanceof Element)) {
/* 344 */       ArrayList localArrayList = null;
/* 345 */       NamedNodeMap localNamedNodeMap = ((Element)paramDHNode.mNode).getAttributes();
/* 346 */       if ((localNamedNodeMap != null) && (localNamedNodeMap.getLength() > 0)) {
/* 347 */         localArrayList = new ArrayList();
/* 348 */         for (int i = 0; i < localNamedNodeMap.getLength(); i++) {
/* 349 */           Attr localAttr = (Attr)localNamedNodeMap.item(i);
/* 350 */           String str1 = localAttr.getName();
/* 351 */           String str2 = localAttr.getValue();
/* 352 */           String str3 = localAttr.getNamespaceURI();
/* 353 */           localArrayList.add(new String[] { str1, str2, str3 });
/*     */         }
/*     */       }
/* 356 */       return localArrayList;
/*     */     }
/* 358 */     return null;
/*     */   }
/*     */ 
/*     */   public static void attrFill2HashMap(HashMap<String, String> paramHashMap, DHNode paramDHNode)
/*     */   {
/* 371 */     if ((paramDHNode != null) && (paramHashMap != null) && ((paramDHNode.mNode instanceof Element))) {
/* 372 */       Element localElement = (Element)paramDHNode.mNode;
/* 373 */       NamedNodeMap localNamedNodeMap = localElement.getAttributes();
/* 374 */       if ((localNamedNodeMap != null) && (localNamedNodeMap.getLength() > 0))
/* 375 */         for (int i = 0; i < localNamedNodeMap.getLength(); i++) {
/* 376 */           Attr localAttr = (Attr)localNamedNodeMap.item(i);
/* 377 */           String str1 = localAttr.getName();
/* 378 */           String str2 = localAttr.getValue();
/* 379 */           paramHashMap.put(str1, str2);
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static DHNode newDocument()
/*     */   {
/* 394 */     DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/*     */ 
/* 396 */     DHNode localDHNode = null;
/*     */     try {
/* 398 */       DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
/* 399 */       localDHNode = new DHNode(localDocumentBuilder.newDocument(), null);
/*     */     } catch (ParserConfigurationException localParserConfigurationException) {
/* 401 */       localParserConfigurationException.printStackTrace();
/*     */     }
/* 403 */     return localDHNode;
/*     */   }
/*     */ 
/*     */   public static boolean isText(DHNode paramDHNode)
/*     */   {
/* 414 */     if ((paramDHNode.mNode instanceof Text)) {
/* 415 */       return true;
/*     */     }
/* 417 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getTextValue(DHNode paramDHNode)
/*     */   {
/* 430 */     return ((Text)paramDHNode.mNode).getNodeValue();
/*     */   }
/*     */ 
/*     */   public static DHNode XML_ParserDocument(InputStream paramInputStream)
/*     */   {
/*     */     try
/*     */     {
/* 443 */       DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/*     */ 
/* 445 */       DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
/*     */ 
/* 447 */       Document localDocument = localDocumentBuilder.parse(paramInputStream);
/* 448 */       return new DHNode(localDocument, null);
/*     */     } catch (Throwable localThrowable) {
/* 450 */       localThrowable.printStackTrace();
/*     */     }
/* 452 */     return null;
/*     */   }
/*     */ 
/*     */   public static DHNode getElementDocument(DHNode paramDHNode)
/*     */   {
/* 462 */     return new DHNode(paramDHNode.mNode.getOwnerDocument(), null);
/*     */   }
/*     */ 
/*     */   public static void removeAttribute(DHNode paramDHNode, String paramString)
/*     */   {
/* 470 */     if (paramDHNode.mNode == null) {
/* 471 */       return;
/*     */     }
/* 473 */     paramDHNode.mNode.getAttributes().removeNamedItem(paramString);
/*     */   }
/*     */ 
/*     */   public static String elementToString(DHNode paramDHNode) {
/* 477 */     StringWriter localStringWriter = new StringWriter();
/*     */ 
/* 487 */     return localStringWriter.toString();
/*     */   }
/*     */ 
/*     */   public static class DHNode
/*     */   {
/*     */     Node mNode;
/*     */ 
/*     */     private DHNode(Node paramNode)
/*     */     {
/* 496 */       this.mNode = paramNode;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object paramObject)
/*     */     {
/* 501 */       if ((paramObject instanceof DHNode)) {
/* 502 */         return this.mNode.equals(((DHNode)paramObject).mNode);
/*     */       }
/* 504 */       return super.equals(paramObject);
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.XmlUtil
 * JD-Core Version:    0.6.2
 */